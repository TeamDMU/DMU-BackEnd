package com.dmforu.admin.message

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class KeywordFilterTest {

    private val keywordFilter = KeywordFilter()

    @DisplayName("제목에서 키워드를 추출한다.")
    @Test
    fun extractKeywordFrom() {
        // given
        val title = "중간고사"

        // when
        val keyword = keywordFilter.extractKeywordFrom(title)

        // then
        assertThat(keyword).isEqualTo("시험")
    }

    @DisplayName("제목에 추출할 키워드가 없다면, null을 반환한다.")
    @Test
    fun extractKeywordFromTitleNotContainingKeyword() {
        // given
        val title = "일반공지"

        // when
        val keyword = keywordFilter.extractKeywordFrom(title)

        // then
        assertThat(keyword).isNull()
    }

    @DisplayName("제목이 비어 있다면, null을 반환한다.")
    @Test
    fun extractKeywordFromEmptyTitle() {
        // given
        val title = ""

        // when
        val keyword = keywordFilter.extractKeywordFrom(title)

        // then
        assertThat(keyword).isNull()
    }

    @DisplayName("제목에 띄어쓰기가 있다면, 띄어쓰기를 무시하고 키워드를 추출한다.")
    @Test
    fun extractKeywordFromTitleWithExtraSpaces() {
        // given
        val title = " 중 간 고 사 "

        // when
        val keyword = keywordFilter.extractKeywordFrom(title)

        // then
        assertThat(keyword).isEqualTo("시험")
    }
}