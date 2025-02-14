package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DepartmentCrawlingPath
import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeReader
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
class DepartmentNoticeCrawlingServiceTest {

    @Mock
    lateinit var departmentNoticeParser: DepartmentNoticeParser

    @Mock
    lateinit var noticeReader: NoticeReader

    @Mock
    lateinit var noticeService: NoticeService

    @Mock
    lateinit var prototypeBeanProvider: ObjectProvider<DepartmentNoticeParser>

    @InjectMocks
    lateinit var service: DepartmentNoticeCrawlingService

    @DisplayName("학과별 공지사항을 크롤링하고, 새로운 공지사항이 있을 경우 저장한다.")
    @Test
    fun addRecentDepartmentNotice() {
        // given
        val notices = listOf(
            mock(Notice::class.java),
            mock(Notice::class.java),
        )

        given(prototypeBeanProvider.getObject()).willReturn(departmentNoticeParser)
        given(noticeReader.findMaxNumberByType(any())).willReturn(1)
        given(noticeService.saveNewNotices(any(), any())).willReturn(false)
        given(departmentNoticeParser.parse(any())).willReturn(notices)

        // when
        service.addRecentDepartmentNotice()

        // then
        verify(prototypeBeanProvider, times(DepartmentCrawlingPath.entries.size)).getObject()
        verify(noticeReader, times(DepartmentCrawlingPath.entries.size)).findMaxNumberByType(any())
    }

}