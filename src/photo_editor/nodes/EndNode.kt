package com.gadstvo69.photo_editor.nodes

import com.gadstvo69.photo_editor.Prerender
import com.gadstvo69.photo_editor.base.BaseImageNode
import com.gadstvo69.photo_editor.base.EndNodeType
import com.gadstvo69.photo_editor.base.LinkInput
import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.scene.layout.RowConstraints
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class EndNode(nodeState: DataFormat, linkState: DataFormat, id: UInt): BaseImageNode(nodeState, linkState, id) {
    private var prerender: Prerender? = null

    lateinit var input: LinkInput<BufferedImage>
    @FXML
    override fun initialize() {
        super.initialize()

        nodeTitle.text = "File OUT"
        input = LinkInput(SwingFXUtils.fromFXImage(image.image, null), this)
        input.onDragDropped = linkDragDroppedHandler
        input.valueProperty.addListener {
                _, _, newValue ->
            valueProperty.value = newValue
            image.image = SwingFXUtils.toFXImage(newValue, null)
        }
        grid.add(input, 0, 2)

        grid.children.remove(deleteButton)

        val saveImageButton = Button("Save image ")
        grid.rowConstraints.add(RowConstraints(100.0))
        grid.add(saveImageButton, 1, 3)
        saveImageButton.setOnAction {
            saveAs()
        }

        image.setOnMouseClicked {
            openPrerender()
        }

        initInputs()

    }

    override fun initOutput() {

    }

    override fun initType(): String = EndNodeType
    override fun initInputs() {
       linkInputs.add(input)
    }

    private fun openPrerender() {

        prerender = Prerender(this)

        try {
            val stage = Stage()
            stage.title = "Prerender"
            stage.scene = Scene(prerender)
            stage.isMaximized = true
            stage.show()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveAs() {
        val fileChooser = FileChooser()
        val extensionFilter = FileChooser.ExtensionFilter("PNG files (*.png)", "*.png")

        fileChooser.extensionFilters.add(extensionFilter)

        var file = fileChooser.showSaveDialog(scene.window as Stage)

        if (file.nameWithoutExtension == file.name) {
            file = File(file.parentFile, file.nameWithoutExtension + ".png")
        }

        try {
            ImageIO.write(valueProperty.value, "png", file)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }
}