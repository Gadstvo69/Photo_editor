package com.gadstvo69.photo_editor.nodes.filter_nodes

import com.gadstvo69.photo_editor.base.FilterNode
import com.gadstvo69.photo_editor.base.LinkInput
import com.gadstvo69.photo_editor.base.RotationNodeType
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin

class RotationNode(nodeState: DataFormat, linkState: DataFormat, id: UInt): FilterNode(nodeState, linkState, id){
    private lateinit var angleInput: LinkInput<Float?>

    @FXML
    override fun initialize() {
        super.initialize()

        angleInput = LinkInput(null, this)

        inputs = mapOf(
            Pair(angleInput, "Angle")
        )

        initInputs()
        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
        val bufferedImage = SwingFXUtils.fromFXImage(img, null)
        val rad = Math.toRadians(angleInput.valueProperty.value!!.toDouble())
        val sin = abs(sin(rad))
        val cos = abs(cos(rad))
        val width = floor(bufferedImage.width * cos + bufferedImage.height * sin).toInt()
        val height = floor(bufferedImage.height * cos + bufferedImage.width * sin).toInt()
        val rotatedImage = BufferedImage(width, height, bufferedImage.type)
        val affineTransform = AffineTransform()

        affineTransform.translate(width / 2.0, height / 2.0)
        affineTransform.rotate(rad, 0.0, 0.0)
        affineTransform.translate(-bufferedImage.width / 2.0, -bufferedImage.height / 2.0)
        val rotateOp = AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR)
        rotateOp.filter(bufferedImage, rotatedImage)
        return SwingFXUtils.toFXImage(rotatedImage, null)
    }

    override fun setTitle() {
        nodeTitle.text = "Rotate"
    }

    override fun initType(): String = RotationNodeType
    override fun initInputs() {
        linkInputs.addAll(
            listOf(
                imageInput,
                angleInput
            )
        )
    }
}