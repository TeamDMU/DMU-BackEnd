package com.dmforu.api.controller.v1

import com.dmforu.api.ControllerTestSupport
import com.dmforu.api.controller.v1.request.RegisterSubscribeRequest
import com.dmforu.api.controller.v1.request.UpdateSubscribeDepartmentRequest
import com.dmforu.api.controller.v1.request.UpdateSubscribeKeywordsRequest
import com.dmforu.api.controller.v1.request.UpdateSubscribeStatusRequest
import com.dmforu.api.support.error.ErrorCode
import com.dmforu.api.support.response.ResultType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class SubscribeControllerTest : ControllerTestSupport() {

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
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("토큰은 필수입니다."))
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
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("학과는 필수입니다."))
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
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("학과 구독 상태는 필수입니다."))
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
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("키워드 구독 상태는 필수입니다."))
    }

    @DisplayName("구독 키워드를 업데이트한다.")
    @Test
    fun updateSubscribeKeywords() {
        // given
        val request = UpdateSubscribeKeywordsRequest(
            token = "0001",
            keywords = listOf("학사"),
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/keywords")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @DisplayName("구독 키워드를 업데이트할 때, 토큰은 필수 값이다.")
    @Test
    fun updateSubscribeKeywordsWithoutToken() {
        // given
        val request = UpdateSubscribeKeywordsRequest(
            token = "",
            keywords = listOf("학사"),
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/keywords")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("토큰은 필수입니다."))
    }

    @DisplayName("구독 키워드를 업데이트할 때, 키워드는 필수 값이 아니다. - 키워드를 전부 지워버릴 수 있기 때문")
    @Test
    fun updateSubscribeKeywordsWithoutKeyword() {
        // given
        val request = UpdateSubscribeKeywordsRequest(
            token = "0001",
            keywords = listOf(),
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/keywords")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @DisplayName("키워드 알림 상태를 변경한다.")
    @Test
    fun updateSubscribeKeywordStatus() {
        // given
        val request = UpdateSubscribeStatusRequest(
            token = "0001",
            subscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/keyword/status")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @DisplayName("키워드 알림 상태를 변경할 때, 토큰은 필수 값이다.")
    @Test
    fun updateSubscribeKeywordStatusWithoutToken() {
        // given
        val request = UpdateSubscribeStatusRequest(
            token = "",
            subscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/keyword/status")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("토큰은 필수입니다."))
    }

    @DisplayName("키워드 알림 상태를 변경할 때, 구독 상태는 필수 값이다.")
    @Test
    fun updateSubscribeKeywordStatusWithoutSubscribeStatus() {
        // given
        val request = UpdateSubscribeStatusRequest(
            token = "0001",
            subscribeStatus = null
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/keyword/status")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("구독 상태는 필수입니다."))
    }

    @DisplayName("구독 학과를 업데이트한다.")
    @Test
    fun updateSubscribeDepartment() {
        // given
        val request = UpdateSubscribeDepartmentRequest(
            token = "0001",
            department = "컴퓨터소프트웨어공학과"
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/department")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @DisplayName("구독 학과를 업데이트할 때, 토큰은 필수 값이다.")
    @Test
    fun updateSubscribeDepartmentWithoutToken() {
        // given
        val request = UpdateSubscribeDepartmentRequest(
            token = "",
            department = "컴퓨터소프트웨어공학과"
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/department")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("토큰은 필수입니다."))
    }

    @DisplayName("구독 학과를 업데이트할 때, 학과는 필수 값이다.")
    @Test
    fun updateSubscribeKeywordsWithoutDepartment() {
        // given
        val request = UpdateSubscribeDepartmentRequest(
            token = "0001",
            department = ""
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/department")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("학과는 필수입니다."))
    }

    @DisplayName("학과 알림 상태를 변경한다.")
    @Test
    fun updateSubscribeDepartmentStatus() {
        // given
        val request = UpdateSubscribeStatusRequest(
            token = "0001",
            subscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/department/status")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @DisplayName("학과 알림 상태를 변경할 때, 토큰은 필수 값이다.")
    @Test
    fun updateSubscribeDepartmentStatusWithoutToken() {
        // given
        val request = UpdateSubscribeStatusRequest(
            token = "",
            subscribeStatus = true
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/department/status")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("토큰은 필수입니다."))
    }

    @DisplayName("키워드 알림 상태를 변경할 때, 구독 상태 필수 값이다.")
    @Test
    fun updateSubscribeDepartmentStatusWithoutSubscribeStatus() {
        // given
        val request = UpdateSubscribeStatusRequest(
            token = "0001",
            subscribeStatus = null
        )

        // when // then
        mockMvc.perform(
            patch("/api/v1/subscribe/department/status")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("구독 상태는 필수입니다."))
    }
}
