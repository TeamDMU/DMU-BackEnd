package com.dmforu.admin.config

import com.dmforu.crawling.loader.JsoupHtmlLoader
import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.crawling.parser.DietParser
import com.dmforu.crawling.parser.ScheduleParser
import com.dmforu.crawling.parser.UniversityNoticeParser
import com.dmforu.domain.diet.DietRepository
import com.dmforu.domain.diet.DietWriter
import com.dmforu.domain.notice.NoticeReader
import com.dmforu.domain.notice.NoticeRepository
import com.dmforu.domain.notice.NoticeWriter
import com.dmforu.domain.schedule.ScheduleRepository
import com.dmforu.domain.schedule.ScheduleWriter
import com.dmforu.domain.subscribe.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class ApplicationConfig {

    @Bean
    fun subscribeReader(subscribeRepository: SubscribeRepository): SubscribeReader {
        return SubscribeReader(subscribeRepository = subscribeRepository)
    }

    @Bean
    fun noticeReader(noticeRepository: NoticeRepository): NoticeReader {
        return NoticeReader(noticeRepository = noticeRepository)
    }

    @Bean
    fun noticeWriter(noticeRepository: NoticeRepository): NoticeWriter {
        return NoticeWriter(noticeRepository = noticeRepository)
    }

    @Bean
    fun scheduleWriter(scheduleRepository: ScheduleRepository): ScheduleWriter {
        return ScheduleWriter(scheduleRepository = scheduleRepository)
    }

    @Bean
    fun dietWriter(dietRepository: DietRepository): DietWriter {
        return DietWriter(dietRepository = dietRepository)
    }

    @Scope("prototype")
    @Bean
    fun departmentNoticeParser(): DepartmentNoticeParser {
        return DepartmentNoticeParser(htmlLoader = JsoupHtmlLoader())
    }

    @Scope("prototype")
    @Bean
    fun universityNoticeParser(): UniversityNoticeParser {
        return UniversityNoticeParser(htmlLoader = JsoupHtmlLoader())
    }

    @Bean
    fun dietParser(): DietParser {
        return DietParser(htmlLoader = JsoupHtmlLoader())
    }

    @Bean
    fun scheduleParser(): ScheduleParser {
        return ScheduleParser(htmlLoader = JsoupHtmlLoader())
    }
}