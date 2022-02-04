package com.gadstvo69.photo_editor.serialization

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import javax.imageio.ImageIO

class DraggableNodeSerializer: JsonSerializer<DraggableNodeState> {
    override fun serialize(src: DraggableNodeState?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val result = JsonObject()
        if (src == null) return result

        val position = src.position
        result.addProperty("x", position.minX)
        result.addProperty("y", position.minY)
        result.addProperty("id", src.id.toString())
        result.addProperty("type", src.type)


        if(src.value is BufferedImage?) {
            var writableImage: ByteArray? = null
            if (src.value != null) {
                val baos = ByteArrayOutputStream()
                ImageIO.write(src.value, "png", baos)
                writableImage = baos.toByteArray()
            }
            result.add("value", context!!.serialize(writableImage))
//            result.addProperty("value", "imageZagluha")
        }
        if (src.value is Int || src.value is String || src.value is Float) {
            result.addProperty("value", src.value.toString())
        }

        return result
    }
}