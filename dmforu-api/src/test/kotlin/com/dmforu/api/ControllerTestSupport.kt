package com.dmforu.api

import com.dmforu.api.controller.v1.DietController
import com.dmforu.api.controller.v1.NoticeController
import com.dmforu.api.controller.v1.ScheduleController
import com.dmforu.api.controller.v1.SubscribeController
import com.dmforu.domain.diet.DietReader
import com.dmforu.domain.notice.NoticeReader
import com.dmforu.domain.schedule.ScheduleReader
import com.dmforu.domain.subscribe.SubscribeUpdater
import com.dmforu.domain.subscribe.SubscribeWriter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(controllers = [DietController::class, NoticeController::class, ScheduleController::class, SubscribeController::class])
abstract class ControllerTestSupport {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @MockBean
    protected lateinit var dietReader: DietReader

    @MockBean
    protected lateinit var noticeReader: NoticeReader

    @MockBean
    protected lateinit var scheduleReader: ScheduleReader

    @MockBean
    protected lateinit var subscribeUpdater: SubscribeUpdater

    @MockBean
    protected lateinit var subscribeWriter: SubscribeWriter

}






