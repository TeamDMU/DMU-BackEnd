package com.dmforu.storage.db.mysql.converter

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {

    private val mapper: ObjectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(datas: List<String>): String {
        try {
            return mapper.writeValueAsString(datas)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException("Error converting list to JSON: ${e.message}", e)
        }
    }

    override fun convertToEntityAttribute(data: String): List<String> {
        try {
            return mapper.readValue(data, object : TypeReference<List<String>>() {})
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException("Error converting JSON to list: ${e.message}", e)
        }
    }
}