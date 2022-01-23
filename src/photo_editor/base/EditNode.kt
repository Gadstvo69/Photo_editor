package com.gadstvo69.photo_editor.base

import javafx.beans.property.SimpleObjectProperty
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.TextField
import javafx.scene.input.DataFormat
import javafx.scene.layout.Border
import javafx.scene.layout.BorderStroke
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Paint

abstract class EditNode<T>(
    nodeState: DataFormat,
    linkState: DataFormat,
    id: UInt,
    private val validatorRegex: Regex
    ): ValueNode<T>(nodeState, linkState, id, FXMLLoader(EditNode::class.java.getResource("EditNode.fxml"))
) {
    protected lateinit var valueOutput: LinkOutput<T>

    @FXML
    lateinit var editField: TextField

    @FXML
    override fun initialize() {
        super.initialize()
        valueOutput = LinkOutput()
        valueOutput.onDragDetected = linkDragDetectedHandler
        outputLayout.children.add(valueOutput)
        initOutput()

        draggedArea.onDragDetected = dragDetectedHandler
        editField.textProperty().addListener {
                _, _, new ->
            validatorRegex ?: return@addListener
            if (!new.matches(validatorRegex)) {
                valueOutput.onDragDetected = null
                editField.border = Border(
                    BorderStroke(
                        Paint.valueOf("FF0000"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii(5.0),
                        BorderStroke.DEFAULT_WIDTHS)
                )
            }
            else {
                valueOutput.onDragDetected = linkDragDetectedHandler
                value = toValue(editField.text)
                link.valueProperty.set(value)
                editField.border = Border(
                    BorderStroke(
                        Paint.valueOf("000000"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii(5.0),
                        BorderStroke.DEFAULT_WIDTHS)
                )
            }
        }
    }

    override fun load(_x: Double, _y: Double, _value: T?) {
        super.load(_x, _y, _value)
        editField.textProperty().set(_value.toString())
    }

    override fun initOutput() {
        output = valueOutput
    }

    abstract fun toValue(text: String): T
}