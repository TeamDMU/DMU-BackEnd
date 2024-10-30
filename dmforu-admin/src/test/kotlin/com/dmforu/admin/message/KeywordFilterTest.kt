package com.dmforu.admin.message

import com.dmforu.domain.message.Keywords.EXAM
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
        val title = "시험"

        // when
        val keyword = keywordFilter.extractKeywordFrom(title)

        // then
        assertThat(keyword).isEqualTo(EXAM.korean)
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
        val title = " 시 험 안 내 "

        // when
        val keyword = keywordFilter.extractKeywordFrom(title)

        // then
        assertThat(keyword).isEqualTo("시험")
    }

    @DisplayName("중간고사, 기말고사는 시험 키워드 반환한다.")
    @Test
    fun extractKeywordFromTitleAboutExam() {
        // given
        val title1 = "중간고사"
        val title2 = "기말고사"

        // when
        val keyword1 = keywordFilter.extractKeywordFrom(title1)
        val keyword2 = keywordFilter.extractKeywordFrom(title2)

        // then
        assertThat(keyword1).isEqualTo(EXAM.korean)
        assertThat(keyword2).isEqualTo(EXAM.korean)
    }
}