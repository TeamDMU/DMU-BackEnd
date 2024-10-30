package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.domain.notice.*
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class DepartmentNoticeCrawlingService(
    private val prototypeBeanProvider: ObjectProvider<DepartmentNoticeParser>,
    private val noticeReader: NoticeReader,
    private val noticeService: NoticeService
) {
    /**
     * Major 열거형의 모든 값을 반복하여 모든 학과의 공지사항을 크롤링한다. <br></br>
     * 데이터베이스에 저장된 공지사항이 존재한다면, 최신 공지사항만 크롤링하여 업데이트 한다. <br></br>
     * 평일 오전 10시, 오후 17시 자동으로 메서드를 실행한다.
     */
    fun addRecentDepartmentNotice() {
        for (major in Major.entries) {
            crawlMajorDepartment(major)
        }
    }

    /**
     * 해당 학과의 모든 공지사항을 크롤링한다. <br></br>
     * 공지사항 첫번째 페이지부터 크롤링을 시작한다. <br></br>
     * 데이터베이스에 저장된 공지사항의 가장 최신 번호와, 파싱된 공지사항 목록을 saveNewNotices 메서드를 통해 저장할지 결정한다. <br></br>
     * 만일, 저장이 이뤄지지 않는다면 더이상 파싱할 필요가 없기 때문에 무한 루트에서 빠져나온다. <br></br>
     *
     * @param major 학과 정보
     */
    private fun crawlMajorDepartment(major: Major) {
        val parser = prototypeBeanProvider.getObject()

        val maxNumber = noticeReader.findMaxNumberByType(major.type)
        val currentMaxNumber = maxNumber ?: 0

        while (true) {
            val notices: List<Notice> = parser.parse(major)
            val isNewNoticeFound = noticeService.saveNewNotices(notices, currentMaxNumber)

            if (!isNewNoticeFound) {
                return
            }
        }
    }

}
