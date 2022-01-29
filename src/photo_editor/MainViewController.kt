package com.gadstvo69.photo_editor

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.MenuItem
import javafx.scene.control.ScrollPane
import javafx.scene.input.DataFormat
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import nu.pattern.OpenCV
import java.awt.image.BufferedImage
import java.io.File

class MainViewController {
    private val nodeState = DataFormat("nodeState")
    private val linkState = DataFormat("linkState")
    private var scene = Scene(nodeState, linkState, 0u)

    @FXML
    private lateinit var sceneContainer: AnchorPane

    @FXML
    private lateinit var sceneScroll: ScrollPane


    @FXML
    private lateinit var addMenuScroll: ScrollPane


    @FXML
    private lateinit var addMenuContainer: VBox

    @FXML
    private lateinit var intNodeButton: MenuItem

    @FXML
    private lateinit var floatNodeButton: MenuItem

    @FXML
    private lateinit var stringNodeButton: MenuItem

    @FXML
    private lateinit var sepiaNodeButton: MenuItem

    @FXML
    private lateinit var addTextNodeButton: MenuItem

    @FXML
    private lateinit var grayNodeButton: MenuItem

    @FXML
    private lateinit var imageNodeButton: MenuItem

    @FXML
    private lateinit var addImageNodeButton: MenuItem

   @FXML
   private lateinit var brightnessNodeButton: MenuItem

   @FXML
   private lateinit var blurNodeButton: MenuItem

   @FXML
   private lateinit var invertNodeButton: MenuItem

   @FXML
   private lateinit var moveNodeButton: MenuItem

   @FXML
   private lateinit var scaleNodeButton: MenuItem

   @FXML
   private lateinit var rotateNodeButton: MenuItem

   @FXML
   private lateinit var saveMenuItem: MenuItem

   @FXML
   private lateinit var openMenuItem: MenuItem

   @FXML
    fun initialize() {
        OpenCV.loadLocally()

        

    }

    private fun <T> addNode(node: DraggableNode<T>) {
        node.onNodeRemovedCallback = {
            scene.remove(it)
        }
        sceneContainer.children.add(node)
        scene.add(node)
    }


    private fun clearScene() {
        sceneContainer.children.clear()
    }

    
    }
}