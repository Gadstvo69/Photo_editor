package com.gadstvo69.photo_editor.nodes.edit_nodes

import com.gadstvo69.photo_editor.base.EditNode
import com.gadstvo69.photo_editor.base.IntNodeType
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class IntNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): EditNode<Int>(nodeState, linkState, id, Regex("^[+-]?\\d+\$")) {
    @FXML
    override fun initialize() {
        super.initialize()
        value = 0
        link.valueProperty.set(value)
        editField.text = "0"
        nodeTitle.text = "Int"
    }

    override fun toValue(text: String): Int = text.toInt()
    override fun initType(): String = IntNodeType
    override fun initInputs() {
    }

}