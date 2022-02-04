package com.gadstvo69.photo_editor.utils

import com.google.gson.JsonArray

fun JsonArray.toByteArray(): ByteArray {
    val byteArray = ByteArray(size())
    for(i in 0 until size()) {
        byteArray[i] = (get(i).asInt and 0xFF).toByte()
    }
    return byteArray
}