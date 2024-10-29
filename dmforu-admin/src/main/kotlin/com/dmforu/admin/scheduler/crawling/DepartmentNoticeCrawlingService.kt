package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.domain.notice.*
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class DepartmentNoticeCrawlingService(
    private val prototypeBeanProvider: ObjectProvider<DepartmentNoticeParser>,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val noticeReader: NoticeReader,
    private val noticeWriter: NoticeWriter,
) {
    /**
     * Major 열거형의 모든 값을 반복하여 모든 학과의 공지사항을 크롤링한다. <br></br>
     * 데이터베이스에 저장된 공지사항이 존재한다면, 최신 공지사항만 크롤링하여 업데이트 한다. <br></br>
     * 평일 오전 10시, 오후 17시 자동으로 메서드를 실행한다.
     */
    fun crawling() {
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

        val maxNumber: Int? = noticeReader.findMaxNumberByType(major.type)
        val currentMaxNumber = maxNumber ?: 0

        while (true) {
            val notices: List<Notice> = parser.parse(major)
            val isNewNoticeFound = saveNewNotices(notices, currentMaxNumber)

            if (!isNewNoticeFound) {
                return
            }
        }
    }

    /**
     * 새로운 학과 공지사항을 저장한다. <br></br>
     * 데이터베이스에 저장된 최신 공지사항 번호보다 큰 번호를 가진 학과 공지사항을 저장한다. <br></br>
     * 데이터베이스에 저장된 공지사항이 존재하지 않아 currentMaxNumber가 0이라면 게시글의 번호가 1번이 될 때 까지 저장한다. <br></br>
     * 위의 조건을 만족하기 전까지 true를 반환하고, 만족하게 되면 false를 반환한다.
     *
     * @param notices           저장할 학과의 공지사항 목록
     * @param currentMaxNumber  현재 데이터베이스에 저장된 최신 공지사항의 번호
     * @return 저장이 성공했다면 true, 그렇지 않다면 false
     */
    private fun saveNewNotices(notices: List<Notice>, currentMaxNumber: Int): Boolean {
        for (notice in notices) {
            if (notice.isNumberLessThanOrEqualTo(currentMaxNumber)) {
                return false
            }

            noticeWriter.write(notice)
            applicationEventPublisher.publishEvent(notice)

            if (notice.isLastInType()) {
                return false
            }
        }

        return true
    }
}
