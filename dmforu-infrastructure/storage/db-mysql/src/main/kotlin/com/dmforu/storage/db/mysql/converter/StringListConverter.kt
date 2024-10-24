package com.dmforu.storage.db.mysql.converter

import com.dmforu.storage.db.mysql.exception.StringListConverterException
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
            throw StringListConverterException("List를 String으로 변환하던 중 문제가 발생했습니다.", e)
        }
    }

    override fun convertToEntityAttribute(data: String): List<String> {
        try {
            return mapper.readValue(data, object : TypeReference<List<String>>() {})
        } catch (e: JsonProcessingException) {
            throw StringListConverterException("String을 List로 변환하던 중 문제가 발생했습니다.", e)
        }
    }
}