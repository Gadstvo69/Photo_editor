package com.gadstvo69.photo_editor.serialization

import com.google.gson.*
import com.gadstvo69.photo_editor.base.*
import com.gadstvo69.photo_editor.nodes.EndNode
import com.gadstvo69.photo_editor.nodes.StartNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.FloatNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.ImageNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.IntNode
import com.gadstvo69.photo_editor.nodes.edit_nodes.StringNode
import com.gadstvo69.photo_editor.nodes.filter_nodes.*
import com.gadstvo69.photo_editor.utils.toByteArray
import javafx.embed.swing.SwingFXUtils
import javafx.scene.input.DataFormat
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.lang.reflect.Type
import java.nio.Buffer
import javax.imageio.ImageIO

class DraggableNodeDeserializer(
    val nodeState: DataFormat,
    val linkState: DataFormat,
): JsonDeserializer<DraggableNode<*>>
{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DraggableNode<*>? {
        val jsonObject = json?.asJsonObject

        return jsonObject?.let { jo ->
            val id = jo.get("id").asInt.toUInt()
            val x = jo.get("x").asDouble
            val y = jo.get("y").asDouble

            when(jo.get("type").asString) {
                IntNodeType -> {
                    val value = jo.get("value").asInt
                    IntNode(nodeState, linkState, id).also { it.load(x, y, value) }
                }
                FloatNodeType -> {
                    val value = jo.get("value").asFloat
                    FloatNode(nodeState, linkState, id).also { it.load(x, y, value) }
                }
                StringNodeType -> {
                    val value = jo.get("value").asString
                    StringNode(nodeState, linkState, id).also {it.load(x, y, value)}
                }
                ImageNodeType ->  {
                    val value = jo.get("value")
                    val bufImage: BufferedImage? = if (value == null) null
                    else ImageIO.read(ByteArrayInputStream(jo.getAsJsonArray("value").toByteArray()))

                    ImageNode(nodeState, linkState, id).also { it.load(x, y, bufImage) }
                }
                EndNodeType -> {
                   EndNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                StartNodeType -> {
                    val value = jo.get("value")
                    val bufImage: BufferedImage? = if (value == null) null
                    else ImageIO.read(ByteArrayInputStream(jo.getAsJsonArray("value").toByteArray()))

                    StartNode(nodeState, linkState, id).also { it.load(x, y, bufImage) }
                }
                AddImageNodeType -> {
                    AddImageNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                AddTextNodeType -> {
                    AddTextNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                BlurNodeType -> {
                    BlurNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                BrightnessNodeType -> {
                    BrightnessNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                GrayFilterNodeType -> {
                    GrayFilterNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                InvertNodeType -> {
                    InvertNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                MoveNodeType -> {
                    MoveNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                RotationNodeType -> {
                    RotationNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                ScaleNodeType -> {
                    ScaleNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                SepiaNodeType -> {
                    SepiaNode(nodeState, linkState, id).also { it.load(x, y, null) }
                }
                else -> null
            }

        }

    }

}