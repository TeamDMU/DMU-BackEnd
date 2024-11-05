package com.dmforu.api.controller.old

import com.dmforu.api.ControllerTestSupport
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OldScheduleControllerTest : ControllerTestSupport() {

    @DisplayName("학사 일정을 불러온다.")
    @Test
    fun readSchedule() {
        // given
        given(scheduleReader.read()).willReturn(listOf())

        // when // then
        mockMvc.perform(
            get("/api/v1/dmu/schedule")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)
    }
}