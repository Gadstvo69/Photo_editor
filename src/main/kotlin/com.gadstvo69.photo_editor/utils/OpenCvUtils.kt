package com.gadstvo69.photo_editor

import javafx.scene.image.Image
import javafx.scene.image.WritablePixelFormat
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import java.io.ByteArrayInputStream

fun imageToMat(image: Image): Mat {
    val width: Int = image.width.toInt()
    val height: Int = image.height.toInt()
    val buffer = ByteArray(width * height * 4)

    val pixelReader = image.pixelReader
    val format = WritablePixelFormat.getByteBgraInstance()
    pixelReader.getPixels(0, 0, width, height, format, buffer, 0, width * 4)

    val mat = Mat(height, width, CvType.CV_8UC4)
    mat.put(0, 0, buffer)
    return mat
}

fun matToImage(mat: Mat): Image {
    val buffer = MatOfByte()
    Imgcodecs.imencode(".png", mat, buffer)
    return Image(ByteArrayInputStream(buffer.toArray()))
}
