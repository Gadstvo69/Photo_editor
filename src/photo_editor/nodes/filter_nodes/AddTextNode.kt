package com.gadstvo69.photo_editor.nodes.filter_nodes

import com.gadstvo69.photo_editor.base.AddTextNodeType
import com.gadstvo69.photo_editor.base.FilterNode
import com.gadstvo69.photo_editor.base.LinkInput
import com.gadstvo69.photo_editor.utils.labelFont
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.scene.layout.RowConstraints
import java.awt.Color
import java.awt.Font

class AddTextNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): FilterNode(nodeState, linkState, id) {
    private lateinit var xInput: LinkInput<Int?>
    private lateinit var yInput: LinkInput<Int?>
    private lateinit var textInput: LinkInput<String?>

    @FXML
    override fun initialize() {
        super.initialize()
        xInput = LinkInput(null, this)
        yInput = LinkInput(null, this)
        textInput = LinkInput(null, this)

        inputs = mapOf(
            Pair(xInput, "x"),
            Pair(yInput, "y"),
            Pair(textInput, "Text")
        )

        initInputs()
        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
        val bufferedImage = SwingFXUtils.fromFXImage(img, null)
        val font = Font("Arial", Font.BOLD, 100)
        val graphics = bufferedImage.graphics
        graphics.font = font
        graphics.color = Color.BLACK
        graphics.drawString(
            textInput.valueProperty.value!!,
            xInput.valueProperty.value!!,
            yInput.valueProperty.value!!
        )

        return SwingFXUtils.toFXImage(bufferedImage, null)
    }

    override fun setTitle() {
        nodeTitle.text = "Add Text"
    }

    override fun initType(): String = AddTextNodeType
    override fun initInputs() {
       linkInputs.addAll(
           listOf(
               imageInput,
               xInput,
               yInput,
               textInput
           )
       )
    }

}