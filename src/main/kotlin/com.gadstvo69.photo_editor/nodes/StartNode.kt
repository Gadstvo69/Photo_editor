package com.gadstvo69.photo_editor.nodes

import com.gadstvo69.photo_editor.base.StartNodeType
import com.gadstvo69.photo_editor.nodes.edit_nodes.ImageNode
import javafx.fxml.FXML
import javafx.scene.input.DataFormat

class StartNode(nodeState: DataFormat, linkState: DataFormat, id: UInt): ImageNode(nodeState, linkState, id) {
    @FXML
    override fun initialize() {
        super.initialize()
        nodeTitle.text = "File IN"
        grid.children.remove(deleteButton)
    }

    override fun initType(): String = StartNodeType
}