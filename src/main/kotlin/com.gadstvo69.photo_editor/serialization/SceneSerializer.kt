package com.gadstvo69.photo_editor.serialization

import com.google.gson.*
import com.gadstvo69.photo_editor.base.Scene
import com.gadstvo69.photo_editor.base.LinkKey
import java.lang.reflect.Type

class SceneSerializer: JsonSerializer<Scene> {
    override fun serialize(src: Scene?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val result = JsonObject()

        if (src == null || context == null) return result

        val nodes = JsonArray()
        val connections = JsonArray()
        for (node in src.nodes) {
            val nodeState = DraggableNodeState(node)
            val serializedNode = context.serialize(nodeState)
            nodes.add(serializedNode)

            val nodeConnections = node.connectedLinks
            for (connection in nodeConnections) {
                val currentConnections = mutableListOf<LinkKey<Int, Int>>()
                currentConnections.add(
                    LinkKey(connection.source.id.toInt(), node.linkInputs.indexOf(connection.destination))
                )
                connections.add(context.serialize(InputLinksState(node.id.toInt(), currentConnections)))
            }
        }

        result.addProperty("currentId", src.getId().toInt())
        result.add("connections", connections)
        result.add("nodes", nodes)

        return result
    }
}