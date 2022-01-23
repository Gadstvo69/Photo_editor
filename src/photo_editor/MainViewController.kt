package com.gadstvo69.photo_editor

import com.gadstvo69.photo_editor.base.DraggableNode
import com.gadstvo69.photo_editor.base.LinkInput
import com.gadstvo69.photo_editor.base.NodeLink
import com.gadstvo69.photo_editor.base.Scene
import com.gadstvo69.photo_editor.nodes.EndNode
import com.gadstvo69.photo_editor.nodes.StartNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.FloatNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.ImageNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.IntNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.StringNode
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

        intNodeButton.setOnAction {
            addNode(IntNode(nodeState, linkState, scene.getId()))
        }

        floatNodeButton.setOnAction {
            addNode(FloatNode(nodeState, linkState, scene.getId()))
        }

        stringNodeButton.setOnAction {
            addNode(StringNode(nodeState, linkState, scene.getId()))
        }

        
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

    private fun loadLinks() {
        for (nodeConnections in scene.connections) {
            val node = scene.findNodeById(nodeConnections.id.toUInt())
            node?.let {
                for (connectionKey in nodeConnections.connections) {
                    val connectedNode = scene.findNodeById(connectionKey.nodeId.toUInt())
                    connectedNode.let {
                        val connectedLink = connectedNode!!.link
                        val currentInput = node.linkInputs[connectionKey.inputId]
                        when {
                            connectedLink.valueProperty.value is Int? && currentInput.valueProperty.value is Int? ->
                                node.loadLink(connectedLink as NodeLink<Int?>, currentInput as LinkInput<Int?>)

                            connectedLink.valueProperty.value is Float? && currentInput.valueProperty.value is Float? ->
                                node.loadLink(connectedLink as NodeLink<Float?>, currentInput as LinkInput<Float?>)

                            connectedLink.valueProperty.value is String? && currentInput.valueProperty.value is String? ->
                                node.loadLink(connectedLink as NodeLink<String?>, currentInput as LinkInput<String?>)

                            connectedLink.valueProperty.value is BufferedImage? && currentInput.valueProperty.value is BufferedImage? ->
                                node.loadLink(
                                    connectedLink as NodeLink<BufferedImage?>,
                                    currentInput as LinkInput<BufferedImage?>
                                )
                        }
                    }
                }
            }
        }
    }
}