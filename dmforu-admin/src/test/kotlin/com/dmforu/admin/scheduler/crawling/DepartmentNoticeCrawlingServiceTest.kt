package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.domain.notice.Major
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeReader
import com.dmforu.domain.notice.NoticeWriter
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
import org.springframework.context.ApplicationEventPublisher

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
        verify(prototypeBeanProvider, times(Major.entries.size)).getObject()
        verify(noticeReader, times(Major.entries.size)).findMaxNumberByType(any())
}

//    @Test
//    fun `현재 최대 번호보다 작거나 같은 공지사항은 저장되지 않아야 한다`() {
//        // Given
//        val major = Major.COMPUTER_SCIENCE // 예시 학과
//        val notices = listOf(
//            Notice(1, "오래된 공지사항", major) // 이 공지사항은 새로운 것이 아님
//        )
//
//        // 모킹된 동작 설정
//        every { prototypeBeanProvider.getObject() } returns departmentNoticeParser
//        every { noticeReader.findMaxNumberByType(major.type) } returns 1
//        every { departmentNoticeParser.parse(major) } returns notices
//
//        // When
//        service.addRecentDepartmentNotice()
//
//        // Then
//        verify { noticeReader.findMaxNumberByType(major.type) }
//        verify(exactly = 0) { noticeWriter.write(any()) } // 저장이 발생하지 않아야 함
//    }

}