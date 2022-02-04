package com.gadstvo69.photo_editor.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.gadstvo69.photo_editor.base.*
import javafx.scene.input.DataFormat
import java.awt.image.BufferedImage
import java.lang.reflect.Type

class SceneDeserializer(val nodeState: DataFormat, val linkState: DataFormat): JsonDeserializer<Scene> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Scene? {
        val jsonObject = json?.asJsonObject
        if(context == null || json == null) return null

        return jsonObject?.let { jo ->
            val currentId = jo.get("currentId").asInt.toUInt()
            val scene = Scene(nodeState, linkState, currentId)
            val nodes = jo.get("nodes").asJsonArray
            for(node in nodes) {
                scene.add(context.deserialize(node, DraggableNode::class.java))
            }

            val jsonConnections = jo.getAsJsonArray("connections")

            for (jsonNodeConnections in jsonConnections) {
                scene.connections.add(context.deserialize(jsonNodeConnections, InputLinksState::class.java))

                }
            scene
        }

    }
}