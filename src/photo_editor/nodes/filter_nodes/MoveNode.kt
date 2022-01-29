package com.gadstvo69.photo_editor.nodes.filter_nodes

import com.gadstvo69.photo_editor.base.FilterNode
import com.gadstvo69.photo_editor.base.LinkInput
import com.gadstvo69.photo_editor.base.MoveNodeType
import com.gadstvo69.photo_editor.imageToMat
import com.gadstvo69.photo_editor.matToImage
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class MoveNode(nodeState: DataFormat, linkState: DataFormat, id: UInt): FilterNode(nodeState, linkState, id){
    private lateinit var xInput: LinkInput<Float?>
    private lateinit var yInput: LinkInput<Float?>

    @FXML
    override fun initialize() {
        super.initialize()

        xInput = LinkInput(null, this)
        yInput = LinkInput(null, this)

        inputs = mapOf(
            Pair(xInput, "x"),
            Pair(yInput, "y")
        )

        initInputs()
        addInputs(3)
        bindInputs()
    }

    override fun filterFunction(img: Image): Image {
        val mat = imageToMat(img)
        val translateMat = Mat(2, 3, CvType.CV_64FC1)
        translateMat.put(
            0,
            0,
            1.0,
            0.0,
            xInput.valueProperty.value!!.toDouble(),
            0.0,
            1.0,
            yInput.valueProperty.value!!.toDouble()
        )

        Imgproc.warpAffine(mat, mat, translateMat, mat.size())
        return matToImage(mat)
    }

    override fun setTitle() {
        nodeTitle.text = "Move"
    }

    override fun initType(): String = MoveNodeType
    override fun initInputs() {
        linkInputs.addAll(
            listOf(
                imageInput,
                xInput,
                yInput
            )
        )
    }
}