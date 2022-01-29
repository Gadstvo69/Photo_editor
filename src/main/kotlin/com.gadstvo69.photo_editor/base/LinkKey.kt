package com.gadstvo69.photo_editor.base

import java.io.Serializable

data class LinkKey<T: Serializable, E: Serializable>(
    val nodeId: T,
    val inputId: E
): Serializable