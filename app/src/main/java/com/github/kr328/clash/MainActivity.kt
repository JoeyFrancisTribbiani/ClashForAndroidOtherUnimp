package com.github.kr328.clash

import android.app.ActivityManager
import android.content.Context
import android.net.VpnService
import android.provider.DocumentsContract
import androidx.activity.result.contract.ActivityResultContracts
import com.github.kr328.clash.common.constants.Authorities
import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.common.util.ticker
import com.github.kr328.clash.design.FilesDesign
import com.github.kr328.clash.design.MainDesign
import com.github.kr328.clash.design.ui.ToastDuration
import com.github.kr328.clash.remote.FilesClient
import com.github.kr328.clash.service.ProfileManager
import com.github.kr328.clash.service.TunService
import com.github.kr328.clash.service.model.Profile
import com.github.kr328.clash.store.TipsStore
import com.github.kr328.clash.util.*
import io.dcloud.feature.barcode2.BarcodeProxy
import io.dcloud.feature.sdk.DCSDKInitConfig
import io.dcloud.feature.sdk.DCUniMPSDK
import io.dcloud.feature.sdk.Interface.IUniMP
import io.dcloud.feature.unimp.DCUniMPJSCallback
import io.dcloud.feature.unimp.config.UniMPOpenConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity<MainDesign>() {
    override suspend fun main() {
        val design = MainDesign(this)

        setContentDesign(design)

        launch(Dispatchers.IO) {
            showUpdatedTips(design)
        }

        design.fetch()
        val vpnRequest = VpnService.prepare(this)
        if (vpnRequest != null){
            startActivityForResult(
            ActivityResultContracts.StartActivityForResult(),
            vpnRequest
            )
        }
        val ticker = ticker(TimeUnit.SECONDS.toMillis(1))

//        val client = FilesClient(this)
        val profileManage = ProfileManager(this)
//        val stack = Stack<String>()
        var root = "88888888-8888-8888-8888-888888888888"
//        var docId = "88888888-8888-8888-8888-888888888888/config.yaml"
        try {
            val uniMPOpenConfiguration = UniMPOpenConfiguration()
            val config = DCSDKInitConfig.Builder()
                .setCapsule(false)
                .setEnableBackground(false)
                .build()
            DCUniMPSDK.getInstance().initialize(this, config)

            DCUniMPSDK.getInstance().setOnUniMPEventCallBack(object : UniMPEventReceiver() {
                override fun onUniMPEventReceive(
                    p0: String?,
                    p1: String?,
                    p2: Any?,
                    p3: DCUniMPJSCallback?
                ) {
                    if (p1 == "startSpeed") {
                        if (p2 != null) {
                            var str = p2.toString()
                            var arr = JSONArray(str)
                            println(arr.javaClass)
                            val peer = arr.getJSONObject(0)
                            var remaining = peer?.getInt("remaining")
                            var remaining_mills: Long = 0
                            if (remaining != null) {
                                remaining_mills = (remaining * 60 * 1000).toLong()
                                if (remaining_mills<0){
                                    remaining_mills = 0
                                }
                            }
                            var processes = peer?.getString("processes")?.split(',')
                            var processStr = """
  - PROCESS-NAME,com.google.android.gms,全局选择
  - PROCESS-NAME,com.google.android.gsf,全局选择
  - PROCESS-NAME,com.android.vending,全局选择
"""
                            if (processes != null) {
                                for (p in processes) {
                                    processStr += "  - PROCESS-NAME,$p,全局选择\n"
                                }
                            }

                            var yamlText = """
port: 7890
socks-port: 7891
allow-lan: false
mode: rule
log-level: info
external-controller: 127.0.0.1:9090
proxies:
- name: "搬瓦工SS节点"
  type: ss                                  # 代理类型
  server: ${peer?.getString("server")}                         # 服务器 IP
  port: ${peer?.getInt("port")}                                 #  端口号
  cipher: chacha20-ietf-poly1305   # 加密方法11111
  password: "${peer?.getString("passwrod")}"                # SS 密码
  udp: true                                #默认不开启
proxy-groups:
  - name: 全局选择
    type: select
    proxies:
      - 搬瓦工SS节点
rules:$processStr
  - MATCH,DIRECT
"""
                            var docId = "88888888-8888-8888-8888-888888888888/config.yaml"
                            launch(Dispatchers.IO) {
                                profileManage.run {
                                    var uuid = UUID.fromString(root)
                                    var name = "加速流量"
                                    create(Profile.Type.File, name)
                                    val target = DocumentsContract.buildDocumentUri(
                                        Authorities.FILES_PROVIDER,
                                        docId
                                    )

                                    var outputStream =
                                        contentResolver.openOutputStream(target, "rwt")
                                    if (outputStream != null) {
                                        outputStream.write(yamlText.encodeToByteArray())
                                    }

                                    patch(uuid, name, "", 0)
                                    commit(uuid)
                                    var profile = Profile(
                                        uuid,
                                        name,
                                        Profile.Type.File,
                                        docId,
                                        true,
                                        0,
                                        System.currentTimeMillis(),
                                        true,
                                        false
                                    )
                                    setActive(profile)
                                    if (clashRunning)
                                        stopClashService()
                                    else {
//                                        TunService.myclock = remaining_mills
                                        design.startClash(remaining_mills)
                                    }
                                }
                            }
                            var status = "stoped"
                            if (clashRunning){
                                status=  "running"
                            }
                            with(p3) { this?.invoke(status) }
                        }
                    }
                    if (p1 == "pause"){
                        stopClashService()
                        var status = "stoped"
                        if (clashRunning){
                            status=  "running"
                        }
                        with(p3) { this?.invoke(status) }
                    }
                    if (p1 == "status"){
                        var status = "stoped"
                        if (clashRunning){
                            status=  "running"
                        }
                        with(p3) { this?.invoke(status) }
                    }
                }
            })
            DCUniMPSDK.getInstance().setUniMPOnCloseCallBack { appid ->
//                val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
//                am.killBackgroundProcesses(packageName)
//                this.finish()
//                finish()
//                Global.destroy()
//                System.exit(0)

                val activityManager =
                    this.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

                var mList = activityManager.getRunningAppProcesses();
                for ( runningAppProcessInfo in mList)
                {
                    if (runningAppProcessInfo.pid != android.os.Process.myPid())
                    {
                        android.os.Process.killProcess(runningAppProcessInfo.pid);
                    }
                }



//
//                val appTaskList = activityManager.appTasks
//                for (appTask in appTaskList) {
//                    appTask.finishAndRemoveTask()
//                }

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
            DCUniMPSDK.getInstance().openUniMP(BarcodeProxy.context, "__UNI__F099CA1", uniMPOpenConfiguration);
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        while (true) {
            select<Unit> {
                events.onReceive {
                    when (it) {
                        Event.ActivityStart,
                        Event.ServiceRecreated,
                        Event.ClashStop, Event.ClashStart,
                        Event.ProfileLoaded, Event.ProfileChanged -> design.fetch()
                        else -> Unit
                    }
                }
                design.requests.onReceive {
                    when (it) {
                        MainDesign.Request.ToggleStatus -> {
                            if (clashRunning)
                                stopClashService()
                            else
                                design.startClash(10000000)
                        }
                        MainDesign.Request.OpenProxy ->
                            startActivity(ProxyActivity::class.intent)
                        MainDesign.Request.OpenProfiles ->
                            startActivity(ProfilesActivity::class.intent)
                        MainDesign.Request.OpenProviders ->
                            startActivity(ProvidersActivity::class.intent)
                        MainDesign.Request.OpenLogs ->
                            startActivity(LogsActivity::class.intent)
                        MainDesign.Request.OpenSettings ->
                            startActivity(SettingsActivity::class.intent)
                        MainDesign.Request.OpenHelp ->
                            startActivity(HelpActivity::class.intent)
                        MainDesign.Request.OpenAbout ->
                            design.showAbout(queryAppVersionName())
                    }
                }
                if (clashRunning) {
                    ticker.onReceive {
                        design.fetchTraffic()
                    }
                }
            }
        }
    }

    private suspend fun showUpdatedTips(design: MainDesign) {
        val tips = TipsStore(this)

        if (tips.primaryVersion != TipsStore.CURRENT_PRIMARY_VERSION) {
            tips.primaryVersion = TipsStore.CURRENT_PRIMARY_VERSION

            val pkg = packageManager.getPackageInfo(packageName, 0)

            if (pkg.firstInstallTime != pkg.lastUpdateTime) {
                design.showUpdatedTips()
            }
        }
    }

    private suspend fun MainDesign.fetch() {
        setClashRunning(clashRunning)

        val state = withClash {
            queryTunnelState()
        }
        val providers = withClash {
            queryProviders()
        }

        setMode(state.mode)
        setHasProviders(providers.isNotEmpty())

        withProfile {
            setProfileName(queryActive()?.name)
        }
    }

    private suspend fun MainDesign.fetchTraffic() {
        withClash {
            setForwarded(queryTrafficTotal())
        }
    }

    private suspend fun MainDesign.startClash(clock :Long) {
        val active = withProfile { queryActive() }

        if (active == null || !active.imported) {
            showToast(R.string.no_profile_selected, ToastDuration.Long) {
                setAction(R.string.profiles) {
                    startActivity(ProfilesActivity::class.intent)
                }
            }

            return
        }

        val vpnRequest = startClashService(clock)

        try {
            if (vpnRequest != null) {
                val result = startActivityForResult(
                    ActivityResultContracts.StartActivityForResult(),
                    vpnRequest
                )

                if (result.resultCode == RESULT_OK)
                    startClashService(clock)
            }
        } catch (e: Exception) {
            design?.showToast(R.string.unable_to_start_vpn, ToastDuration.Long)
        }
    }

    private suspend fun queryAppVersionName(): String {
        return withContext(Dispatchers.IO) {
            packageManager.getPackageInfo(packageName, 0).versionName
        }
    }
//
//    private suspend fun FilesDesign.fetch(client: FilesClient, stack: Stack<String>, root: String) {
//        val documentId = stack.lastOrNull() ?: root
//        val files = if (stack.empty()) {
//            val list = client.list(documentId)
//            val config = list.firstOrNull { it.id.endsWith("config.yaml") }
//
//            if (config == null || config.size > 0) list else listOf(config)
//        } else {
//            client.list(documentId)
//        }
//
//        swapFiles(files, stack.empty())
//    }

}