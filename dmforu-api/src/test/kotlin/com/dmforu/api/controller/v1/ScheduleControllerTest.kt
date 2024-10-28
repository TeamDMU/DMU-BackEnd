package com.dmforu.api.controller.v1

import com.dmforu.api.support.response.ResultType
import com.dmforu.domain.schedule.ScheduleReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ScheduleController::class)
class ScheduleControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var scheduleReader: ScheduleReader

    @DisplayName("식단을 불러온다.")
    @Test
    fun readDiet() {
        // given
        given(scheduleReader.read()).willReturn(listOf())

        // when // then
        mockMvc.perform(
            get("/api/v1/schedule")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)
            .andExpect(jsonPath("$.error").isEmpty)
    }
}
