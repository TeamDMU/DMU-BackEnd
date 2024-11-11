package com.dmforu.api.controller.old

import com.dmforu.api.ControllerTestSupport
import com.dmforu.api.controller.old.request.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OldSubscribeControllerTest : ControllerTestSupport() {

    @DisplayName("구독을 생성한다.")
    @Test
    fun tokenSubscribe() {
        // given
        val request = OldRegisterSubscribeRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            topic = listOf(Topic.exam, Topic.work)
        )

        // when // then
        mockMvc.perform(
            post("/token/v1/dmu/initToken")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @DisplayName("학과를 업데이트하며, 학과 구독 상태를 구독으로 변경한다.")
    @Test
    fun departmentSubscribe() {
        // given
        val request = OldDepartmentRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
        )

        // when // then
        mockMvc.perform(
            post("/department/v1/dmu/updateDepartment")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @DisplayName("학과 구독 상태를 취소로 변경한다.")
    @Test
    fun departmentUnsubscribe() {
        // given
        val request = OldTokenRequest(
            token = "0001"
        )

        // when // then
        mockMvc.perform(
            post("/department/v1/dmu/deleteDepartment")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @DisplayName("키워드를 업데이트하며, 키워드 구독 상태를 구독으로 변경한다.")
    @Test
    fun keywordsSubscribe() {
        // given
        val request = OldKeywordsSubscribeRequest(
            token = "0001",
            topics = listOf(Topic.exam, Topic.work)
        )

        // when // then
        mockMvc.perform(
            post("/token/v1/dmu/updateTopic")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @DisplayName("키워드 구독 상태를 취소로 변경한다.")
    @Test
    fun keywordsUnsubscribe() {
        // given
        val request = OldTokenRequest(
            token = "0001"
        )

        // when // then
        mockMvc.perform(
            post("/token/v1/dmu/deleteTopic")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }
}