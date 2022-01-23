package com.gadstvo69.photo_editor.nodes.edit_nodes

import com.gadstvo69.photo_editor.base.EditNode
import com.gadstvo69.photo_editor.base.StringNodeType
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StringNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): EditNode<String>(nodeState, linkState, id, Regex("^[\\s\\S]*")) {

    @FXML
    override fun initialize() {
        super.initialize()
        value = ""
        link.valueProperty.set(value)
        editField.text = ""
        nodeTitle.text = "String"
    }

    override fun toValue(text: String): String = text
    override fun initType(): String = StringNodeType
    override fun initInputs() {
    }
}