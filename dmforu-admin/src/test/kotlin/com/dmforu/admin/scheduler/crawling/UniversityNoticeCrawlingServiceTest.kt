package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.UniversityNoticeParser
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.springframework.beans.factory.ObjectProvider

@ExtendWith(MockitoExtension::class)
class UniversityNoticeCrawlingServiceTest {

    @Mock
    lateinit var universityNoticeParser: UniversityNoticeParser

    @Mock
    lateinit var noticeReader: NoticeReader

    @Mock
    lateinit var noticeService: NoticeService

    @Mock
    lateinit var prototypeBeanProvider: ObjectProvider<UniversityNoticeParser>

    @InjectMocks
    lateinit var service: UniversityNoticeCrawlingService

    @DisplayName("대학 공지사항을 크롤링하고, 새로운 공지사항이 있을 경우 저장한다.")
    @Test
    fun addRecentUniversityNotice() {
        // given
        val notices = listOf(
            mock(Notice::class.java),
            mock(Notice::class.java),
        )

        given(prototypeBeanProvider.getObject()).willReturn(universityNoticeParser)
        given(noticeReader.findMaxNumberByType(any())).willReturn(1)
        given(noticeService.saveNewNotices(any(), any())).willReturn(false)
        given(universityNoticeParser.parse()).willReturn(notices)

        // when
        service.addRecentUniversityNotice()

        // then
        verify(prototypeBeanProvider).getObject()
        verify(noticeReader).findMaxNumberByType(any())
    }

}