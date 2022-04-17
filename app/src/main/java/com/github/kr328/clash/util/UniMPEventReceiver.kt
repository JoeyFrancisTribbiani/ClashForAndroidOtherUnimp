package com.github.kr328.clash.util

import com.github.kr328.clash.BaseActivity
import com.github.kr328.clash.design.FilesDesign
import com.github.kr328.clash.remote.FilesClient
import com.github.kr328.clash.service.model.Profile
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack
import io.dcloud.feature.unimp.DCUniMPJSCallback
import java.util.*

open class UniMPEventReceiver:IOnUniMPEventCallBack {
    override fun onUniMPEventReceive(p0: String?, p1: String?, p2: Any?, p3: DCUniMPJSCallback?) {
        TODO("Not yet implemented")
    }

}

@JsonClass(generateAdapter = true)
data class Peer(
    val game_package: String,
    val peer_name: String,
    val server: String,
    val port: Int,
    val cipher: String,
    val passwrod: String
)