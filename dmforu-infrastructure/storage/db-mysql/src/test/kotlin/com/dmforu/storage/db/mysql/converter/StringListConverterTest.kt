package com.dmforu.storage.db.mysql.converter

import com.dmforu.storage.db.mysql.exception.StringListConverterException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class StringListConverterTest {

    private val converter: StringListConverter = StringListConverter()

    @DisplayName("List에서 String으로 직렬화 할 수 있다.")
    @Test
    fun convertToDatabaseColumn() {
        // given
        val list = listOf("테스트1", "테스트2")

        // when
        val data = converter.convertToDatabaseColumn(list)

        // then
        assertThat(data).isEqualTo("[\"테스트1\",\"테스트2\"]")
    }

    @DisplayName("List에서 String으로 직렬화 하던 중, 문제가 발생하면 StringListConverterException 예외를 발생시킨다.")
    @Test
    fun convertToDatabaseColumnThrowException() {
        // given
        val mockMapper = mock(ObjectMapper::class.java)
        val converter = StringListConverter()
        val testList = listOf("invalid")

        given(mockMapper.writeValueAsString(testList)).willThrow(JsonProcessingException::class.java)

        setObjectMapper(converter, mockMapper)

        // when // then
        assertThatThrownBy { converter.convertToDatabaseColumn(testList) }
            .isInstanceOf(StringListConverterException::class.java)
            .hasMessage("List를 String으로 변환하던 중 문제가 발생했습니다.")
    }

    @DisplayName("String에서 List로 역 직렬화 할 수 있다.")
    @Test
    fun convertToEntityAttribute() {
        // given
        val data = "[\"테스트1\",\"테스트2\"]"

        // when
        val list = converter.convertToEntityAttribute(data)

        // then
        assertThat(list).isEqualTo(listOf("테스트1", "테스트2"))
    }

    @DisplayName("String에서 List로 역 직렬화 하던 중, 문제가 발생하면 StringListConverterException 예외를 발생시킨다.")
    @Test
    fun convertToEntityAttributeThrowException() {
        // given
        val mockMapper = mock(ObjectMapper::class.java)
        val converter = StringListConverter()
        val test = "invalid"

        given(
            mockMapper.readValue(eq(test), any<TypeReference<List<String>>>())
        ).willThrow(JsonProcessingException::class.java)

        setObjectMapper(converter, mockMapper)

        // when // then
        assertThatThrownBy { converter.convertToEntityAttribute(test) }
            .isInstanceOf(StringListConverterException::class.java)
            .hasMessage("String을 List로 변환하던 중 문제가 발생했습니다.")
    }

    private fun setObjectMapper(converter: StringListConverter, objectMapper: ObjectMapper) {
        val mapperField = StringListConverter::class.java.getDeclaredField("mapper")
        mapperField.isAccessible = true
        mapperField.set(converter, objectMapper)
    }
}


