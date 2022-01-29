package com.gadstvo69.photo_editor.nodes.filter_nodes

import com.gadstvo69.photo_editor.base.AddImageNodeType
import com.gadstvo69.photo_editor.base.FilterNode
import com.gadstvo69.photo_editor.base.LinkInput
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage

class AddImageNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): FilterNode(nodeState, linkState, id) {
    private lateinit var xInput: LinkInput<Int?>
    private lateinit var yInput: LinkInput<Int?>
    private lateinit var addingImageInput: LinkInput<BufferedImage?>

    @FXML
    override fun initialize() {
        super.initialize()

        xInput = LinkInput(null, this)
        yInput = LinkInput(null, this)
        addingImageInput = LinkInput(null, this)

        inputs = mapOf(
            Pair(addingImageInput, "Image"),
            Pair(xInput, "x"),
            Pair(yInput, "y")
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
        graphics.drawImage(
            addingImageInput.valueProperty.value!!,
            xInput.valueProperty.value!!,
            yInput.valueProperty.value!!,
            null
        )

        return SwingFXUtils.toFXImage(bufferedImage, null)
    }

    override fun setTitle() {
        nodeTitle.text = "Add Image"
    }

    override fun initType(): String = AddImageNodeType

    override fun initInputs() {
       linkInputs.addAll(listOf(
           imageInput,
           xInput,
           yInput,
           addingImageInput
       ))
    }
}