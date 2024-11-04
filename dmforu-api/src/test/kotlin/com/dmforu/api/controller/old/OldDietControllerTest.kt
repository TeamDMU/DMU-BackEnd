package com.dmforu.api.controller.old

import com.dmforu.api.OldControllerTestSupport
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OldDietControllerTest : OldControllerTestSupport() {

    @DisplayName("식단을 불러온다.")
    @Test
    fun readDiet() {
        mockMvc.perform(
            get("/api/v1/dmu/cafeteria")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)
    }

}