//package com.github.kr328.clash.service
//
//import android.content.Context
//import com.github.kr328.clash.service.data.Database
//import com.github.kr328.clash.service.data.ImportedDao
//import com.github.kr328.clash.service.data.Pending
//import com.github.kr328.clash.service.data.PendingDao
//import com.github.kr328.clash.service.model.Profile
//import com.github.kr328.clash.service.remote.IFetchObserver
//import com.github.kr328.clash.service.remote.IProfileManager
//import com.github.kr328.clash.service.store.ServiceStore
//import com.github.kr328.clash.service.util.directoryLastModified
//import com.github.kr328.clash.service.util.generateProfileUUID
//import com.github.kr328.clash.service.util.importedDir
//import com.github.kr328.clash.service.util.pendingDir
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.io.FileNotFoundException
//import java.util.*
//
//class MyProfileManager(private val context: Context) {
//
//    private val store = ServiceStore(context)
//
////    init {
////        launch {
////            Database.database //.init
////
////            ProfileReceiver.rescheduleAll(context)
////        }
////    }
//
//     fun create(type: Profile.Type, name: String, source: String): UUID {
//        val uuid = generateProfileUUID()
//        val pending = Pending(
//            uuid = uuid,
//            name = name,
//            type = type,
//            source = source,
//            interval = 0,
//        )
//
//        PendingDao().insert(pending)
//
//        context.pendingDir.resolve(uuid.toString()).apply {
//            deleteRecursively()
//            mkdirs()
//
//            @Suppress("BlockingMethodInNonBlockingContext")
//            resolve("config.yaml").createNewFile()
//            resolve("providers").mkdir()
//        }
//
//        return uuid
//    }
//
//     fun clone(uuid: UUID): UUID {
//        val newUUID = generateProfileUUID()
//
//        val imported = ImportedDao().queryByUUID(uuid)
//            ?: throw FileNotFoundException("profile $uuid not found")
//
//        val pending = Pending(
//            uuid = newUUID,
//            name = imported.name,
//            type = Profile.Type.File,
//            source = imported.source,
//            interval = imported.interval,
//        )
//
//        cloneImportedFiles(uuid, newUUID)
//
//        PendingDao().insert(pending)
//
//        return newUUID
//    }
//
//     fun patch(uuid: UUID, name: String, source: String, interval: Long) {
//        val pending = PendingDao().queryByUUID(uuid)
//
//        if (pending == null) {
//            val imported = ImportedDao().queryByUUID(uuid)
//                ?: throw FileNotFoundException("profile $uuid not found")
//
//            cloneImportedFiles(uuid)
//
//            PendingDao().insert(
//                Pending(
//                    uuid = imported.uuid,
//                    name = name,
//                    type = imported.type,
//                    source = source,
//                    interval = interval,
//                )
//            )
//        } else {
//            val newPending = pending.copy(
//                name = name,
//                source = source,
//                interval = interval
//            )
//
//            PendingDao().update(newPending)
//        }
//    }
//
//     fun update(uuid: UUID) {
//        scheduleUpdate(uuid, true)
//    }
//
//     fun commit(uuid: UUID, callback: IFetchObserver?) {
//        ProfileProcessor.apply(context, uuid, callback)
//
//        scheduleUpdate(uuid, false)
//    }
//
//     fun release(uuid: UUID) {
//        ProfileProcessor.release(context, uuid)
//    }
//
//     fun delete(uuid: UUID) {
//        ImportedDao().queryByUUID(uuid)?.also {
//            ProfileReceiver.cancelNext(context, it)
//        }
//
//        ProfileProcessor.delete(context, uuid)
//    }
//
//     fun queryByUUID(uuid: UUID): Profile? {
//        return resolveProfile(uuid)
//    }
//
//     fun queryAll(): List<Profile> {
//        val uuids = withContext(Dispatchers.IO) {
//            (ImportedDao().queryAllUUIDs() + PendingDao().queryAllUUIDs()).distinct()
//        }
//
//        return uuids.mapNotNull { resolveProfile(it) }
//    }
//
//     fun queryActive(): Profile? {
//        val active = store.activeProfile ?: return null
//
//        return if (ImportedDao().exists(active)) {
//            resolveProfile(active)
//        } else {
//            null
//        }
//    }
//
//     fun setActive(profile: Profile) {
//        ProfileProcessor.active(context, profile.uuid)
//    }
//
//    private fun resolveProfile(uuid: UUID): Profile? {
//        val imported = ImportedDao().queryByUUID(uuid)
//        val pending = PendingDao().queryByUUID(uuid)
//
//        val active = store.activeProfile
//        val name = pending?.name ?: imported?.name ?: return null
//        val type = pending?.type ?: imported?.type ?: return null
//        val source = pending?.source ?: imported?.source ?: return null
//        val interval = pending?.interval ?: imported?.interval ?: return null
//
//        return Profile(
//            uuid,
//            name,
//            type,
//            source,
//            active != null && imported?.uuid == active,
//            interval,
//            resolveUpdatedAt(uuid),
//            imported != null,
//            pending != null
//        )
//    }
//
//    private fun resolveUpdatedAt(uuid: UUID): Long {
//        return context.pendingDir.resolve(uuid.toString()).directoryLastModified
//            ?: context.importedDir.resolve(uuid.toString()).directoryLastModified
//            ?: -1
//    }
//
//    private fun cloneImportedFiles(source: UUID, target: UUID = source) {
//        val s = context.importedDir.resolve(source.toString())
//        val t = context.pendingDir.resolve(target.toString())
//
//        if (!s.exists())
//            throw FileNotFoundException("profile $source not found")
//
//        t.deleteRecursively()
//
//        s.copyRecursively(t)
//    }
//
//    private fun scheduleUpdate(uuid: UUID, startImmediately: Boolean) {
//        val imported = ImportedDao().queryByUUID(uuid) ?: return
//
//        if (startImmediately) {
//            ProfileReceiver.schedule(context, imported)
//        } else {
//            ProfileReceiver.scheduleNext(context, imported)
//        }
//    }
//}