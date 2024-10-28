package com.dmforu.api.controller.v1

import com.dmforu.api.controller.v1.request.RegisterSubscribeRequest
import com.dmforu.api.support.error.ErrorCode
import com.dmforu.api.support.response.ResultType
import com.dmforu.domain.subscribe.SubscribeUpdater
import com.dmforu.domain.subscribe.SubscribeWriter
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(SubscribeController::class)
class SubscribeControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var subscribeUpdater: SubscribeUpdater

    @MockBean
    private lateinit var subscribeWriter: SubscribeWriter

    @DisplayName("구독을 생성한다.")
    @Test
    fun initSubscribe() {
        // given
        val request = RegisterSubscribeRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("학사"),
            departmentSubscribeStatus = true,
            keywordSubscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            post("/api/v1/subscribe/registration")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.CREATE.name))
            .andExpect(jsonPath("$.data").isEmpty)
            .andExpect(jsonPath("$.error").isEmpty)
    }

    @DisplayName("구독을 생성할 때, 토큰은 필수 값이다.")
    @Test
    fun initSubscribeWithoutToken() {
        // given
        val request = RegisterSubscribeRequest(
            token = "",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("학사"),
            departmentSubscribeStatus = true,
            keywordSubscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            post("/api/v1/subscribe/registration")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data").isEmpty)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").value("토큰은 필수입니다."))
    }

    @DisplayName("구독을 생성할 때, 학과는 필수 값이다.")
    @Test
    fun initSubscribeWithoutDepartment() {
        // given
        val request = RegisterSubscribeRequest(
            token = "0001",
            department = "",
            keywords = listOf("학사"),
            departmentSubscribeStatus = true,
            keywordSubscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            post("/api/v1/subscribe/registration")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data").isEmpty)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").value("학과는 필수입니다."))
    }

    @DisplayName("구독을 생성할 때, 키워드는 필수 값이 아니다.")
    @Test
    fun initSubscribeWithoutKeyword() {
        // given
        val request = RegisterSubscribeRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf(),
            departmentSubscribeStatus = true,
            keywordSubscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            post("/api/v1/subscribe/registration")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.CREATE.name))
            .andExpect(jsonPath("$.data").isEmpty)
            .andExpect(jsonPath("$.error").isEmpty)
    }

    @DisplayName("구독을 생성할 때, 학과 구독 상태는 필수 값이다.")
    @Test
    fun initSubscribeWithoutDepartmentSubscribeStatus() {
        // given
        val request = RegisterSubscribeRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf(),
            departmentSubscribeStatus = null,
            keywordSubscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            post("/api/v1/subscribe/registration")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data").isEmpty)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").value("학과 구독 상태는 필수입니다."))
    }

    @DisplayName("구독을 생성할 때, 키워드 구독 상태는 필수 값이다.")
    @Test
    fun initSubscribeWithoutKeywordsSubscribeStatus() {
        // given
        val request = RegisterSubscribeRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("학사"),
            departmentSubscribeStatus = true,
            keywordSubscribeStatus = null
        )

        // when // then
        mockMvc.perform(
            post("/api/v1/subscribe/registration")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data").isEmpty)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").value("키워드 구독 상태는 필수입니다."))
    }
}
