package com.gadstvo69.photo_editor.nodes.filter_nodes

import com.gadstvo69.photo_editor.base.BlurNodeType
import com.gadstvo69.photo_editor.base.FilterNode
import com.gadstvo69.photo_editor.base.LinkInput
import com.gadstvo69.photo_editor.imageToMat
import com.gadstvo69.photo_editor.matToImage
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

class BlurNode(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt
): FilterNode(nodeState, linkState, id) {
    private lateinit var kernelSizeInput: LinkInput<Int?>

    @FXML
    override fun initialize() {
        super.initialize()
        kernelSizeInput = LinkInput(null, this)

        inputs = mapOf(
            Pair(kernelSizeInput, "Kernel size")
        )

        initInputs()
        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
        val src = imageToMat(img)
        val res = Mat()
        var kernelSize = kernelSizeInput.valueProperty.value!!
        if (kernelSize > 300) kernelSize = 300
        if (kernelSize % 2 == 0) kernelSize += 1

        Imgproc.GaussianBlur(src, res, Size(kernelSize.toDouble(), kernelSize.toDouble()), 0.0)
        return matToImage(res)
    }

    override fun setTitle() {
        nodeTitle.text = "Blur"
    }

    override fun initType(): String = BlurNodeType
    override fun initInputs() {
        linkInputs.addAll(
            listOf(
                imageInput,
                kernelSizeInput
            )
        )

    }

}