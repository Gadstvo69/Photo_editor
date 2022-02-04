package com.gadstvo69.photo_editor.serialization

import com.gadstvo69.photo_editor.base.LinkKey
import java.io.Serializable

data class InputLinksState (
    val id: Int,
    val connections: MutableList<LinkKey<Int, Int>>
    ): Serializable