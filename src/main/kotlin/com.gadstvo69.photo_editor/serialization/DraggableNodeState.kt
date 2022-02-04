package com.gadstvo69.photo_editor.serialization

import com.gadstvo69.photo_editor.base.DraggableNode
import javafx.geometry.Bounds

class DraggableNodeState(node: DraggableNode<*>) {
    val position: Bounds = node.boundsInParent
    val id = node.id
    val type = node.type
    val value = node.value
}