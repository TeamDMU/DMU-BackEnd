package com.dmforu.admin.message

import com.dmforu.domain.message.MessageSender
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.subscribe.SubscribeReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class MessageServiceTest {

    @Mock
    private lateinit var keywordFilter: KeywordFilter

    @Mock
    private lateinit var subscribeReader: SubscribeReader

    @Mock
    private lateinit var messageSender: MessageSender

    @InjectMocks
    private lateinit var messageService: MessageService

    @DisplayName("대학 공지사항은 키워드를 추출하고, 키워드에 해당하는 토큰을 불러와 푸시 알림을 전송한다.")
    @Test
    fun sendUniversityNoticeMessage() {
        // given
        val universityNotice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 10),
            title = "중간고사 안내",
            author = "저자",
            url = "http://test.com",
        )

        val tokens = listOf("토큰1", "토큰2")

        given(keywordFilter.extractKeywordFrom(universityNotice.title)).willReturn("시험")
        given(subscribeReader.getTokensBySubscribedToKeyword(any())).willReturn(tokens)

        // when
        messageService.sendNoticeMessage(universityNotice)

        // then
        verify(messageSender).sendNoticeMessage(any(), eq(tokens))
    }

    @DisplayName("키워드에 해당하지 않는 경우, 푸시 알림을 전송하지 않는다.")
    @Test
    fun sendUniversityNoticeMessageWithoutKeyword() {
        // given
        val universityNotice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 10),
            title = "제목",
            author = "저자",
            url = "http://test.com",
        )

        val tokens = listOf("토큰1", "토큰2")

        given(keywordFilter.extractKeywordFrom(universityNotice.title)).willReturn(null)

        // when
        messageService.sendNoticeMessage(universityNotice)

        // then
        verify(messageSender, never()).sendNoticeMessage(any(), eq(tokens))

    }

    @DisplayName("키워드에 해당하는 토큰이 없는 경우, 푸시 알림을 전송하지 않는다.")
    @Test
    fun sendUniversityNoticeMessageWithoutTokens() {
        // given
        val universityNotice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 10),
            title = "중간고사 안내",
            author = "저자",
            url = "http://test.com",
        )

        val tokens = listOf("토큰1", "토큰2")

        given(keywordFilter.extractKeywordFrom(universityNotice.title)).willReturn("시험")
        given(subscribeReader.getTokensBySubscribedToKeyword(any())).willReturn(listOf())

        // when
        messageService.sendNoticeMessage(universityNotice)

        // then
        verify(messageSender, never()).sendNoticeMessage(any(), eq(tokens))

    }

    @DisplayName("학과 공지사항은 학과에 해당하는 토큰을 불러와 푸시 알림을 전송한다.")
    @Test
    fun sendDepartmentNoticeMessage() {
        // given
        val departmentNotice = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 10),
            title = "중간고사 안내",
            author = "저자",
            url = "http://test.com",
        )

        val tokens = listOf("토큰1", "토큰2")

        given(subscribeReader.getTokensBySubscribedToDepartment(any())).willReturn(tokens)

        // when
        messageService.sendNoticeMessage(departmentNotice)

        // then
        verify(messageSender).sendNoticeMessage(any(), eq(tokens))
    }

    @DisplayName("학과에 해당하는 토큰이 없는 경우, 푸시 알림을 전송하지 않는다.")
    @Test
    fun sendDepartmentNoticeMessageWithoutTokens() {
        // given
        val departmentNotice = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 10),
            title = "중간고사 안내",
            author = "저자",
            url = "http://test.com",
        )

        val tokens = listOf("토큰1", "토큰2")

        given(subscribeReader.getTokensBySubscribedToDepartment(any())).willReturn(listOf())

        // when
        messageService.sendNoticeMessage(departmentNotice)

        // then
        verify(messageSender, never()).sendNoticeMessage(any(), eq(tokens))
    }
}