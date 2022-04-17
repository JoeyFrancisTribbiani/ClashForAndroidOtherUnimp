package com.github.kr328.clash.service.util

import com.github.kr328.clash.service.data.ImportedDao
import com.github.kr328.clash.service.data.PendingDao
import java.util.*

suspend fun generateProfileUUID(): UUID {
//    var result = UUID.randomUUID()
//
//    while (ImportedDao().exists(result) || PendingDao().exists(result)) {
//        result = UUID.randomUUID()
//    }
    var result = UUID.fromString("88888888-8888-8888-8888-888888888888")
    return result
}
