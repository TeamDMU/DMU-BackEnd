package com.dmforu.api.controller.v1

import com.dmforu.api.ControllerTestSupport
import com.dmforu.api.support.error.ErrorCode
import com.dmforu.api.support.response.ResultType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class NoticeControllerTest : ControllerTestSupport() {

    @DisplayName("대학 공지를 불러온다.")
    @Test
    fun getUniversityNotice() {
        // given
        given(noticeReader.readUniversityNotice(anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/notice/university")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)
    }

    @DisplayName("학과 공지를 불러온다.")
    @Test
    fun getDepartmentNotice() {
        // given
        given(noticeReader.readDepartmentNotice(anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/notice/department")
                .param("department", "컴퓨터소프트웨어공학과")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)
    }

    @DisplayName("학과 공지를 불러올 때, 학과는 필수 값이다.")
    @Test
    fun getDepartmentNoticeWhenDepartmentIsEmpty() {
        mockMvc.perform(
            get("/api/v1/notice/department")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").isEmpty)
    }


    @DisplayName("공지를 검색한다.")
    @Test
    fun getNoticeByKeyword() {
        // given
        given(noticeReader.searchNotice(anyString(), anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/notice/공지사항")
                .param("department", "컴퓨터소프트웨어공학과")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)
    }

    @DisplayName("공지를 검색할 때, 학과는 필수 값이다.")
    @Test
    fun getNoticeByKeywordWhen() {
        mockMvc.perform(
            get("/api/v1/notice/search")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").isEmpty)
    }

    @DisplayName("공지를 검색할 때, 검색어는 필수 값이다. ")
    @Test
    fun getNoticeByKeywordWhenPathValueIsEmpty() {
        mockMvc.perform(
            get("/api/v1/notice/")
                .param("department", "컴퓨터소프트웨어공학과")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E404.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 URL로 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").isEmpty)
    }

    @DisplayName("공지를 불러올 때, 페이지네이션 값이 빠져있다면 페이지 1, 사이즈 20으로 자동 설정된다.")
    @Test
    fun getNoticeWithoutPagination() {
        // given
        given(noticeReader.readUniversityNotice(anyInt(), anyInt())).willReturn(listOf())
        given(noticeReader.readDepartmentNotice(anyString(), anyInt(), anyInt())).willReturn(listOf())
        given(noticeReader.searchNotice(anyString(), anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/notice/university")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)

        mockMvc.perform(
            get("/api/v1/notice/department")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)

        mockMvc.perform(
            get("/api/v1/notice/공지사항")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(ResultType.SUCCESS.name))
            .andExpect(jsonPath("$.data").isArray)
    }

    @DisplayName("공지를 불러올 때, 페이지의 값은 1이상이어야 한다.")
    @Test
    fun getNoticeWhenPageIsLessThenOne() {
        // given
        given(noticeReader.readUniversityNotice(anyInt(), anyInt())).willReturn(listOf())
        given(noticeReader.readDepartmentNotice(anyString(), anyInt(), anyInt())).willReturn(listOf())
        given(noticeReader.searchNotice(anyString(), anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/notice/university")
                .param("page", "0")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("페이지는 1이상이어야 합니다."))

        mockMvc.perform(
            get("/api/v1/notice/department")
                .param("page", "0")
                .param("size", "20")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("페이지는 1이상이어야 합니다."))

        mockMvc.perform(
            get("/api/v1/notice/공지사항")
                .param("page", "0")
                .param("size", "20")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("페이지는 1이상이어야 합니다."))
    }

    @DisplayName("공지를 불러올 때, 사이즈의 값은 1이상이어야 한다.")
    @Test
    fun getNoticeWhenSizeIsLessThenOne() {
        // given
        given(noticeReader.readUniversityNotice(anyInt(), anyInt())).willReturn(listOf())
        given(noticeReader.readDepartmentNotice(anyString(), anyInt(), anyInt())).willReturn(listOf())
        given(noticeReader.searchNotice(anyString(), anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/notice/university")
                .param("page", "1")
                .param("size", "0")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("사이즈는 1이상이어야 합니다."))

        mockMvc.perform(
            get("/api/v1/notice/department")
                .param("page", "1")
                .param("size", "0")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("사이즈는 1이상이어야 합니다."))

        mockMvc.perform(
            get("/api/v1/notice/공지사항")
                .param("page", "1")
                .param("size", "0")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.result").value(ResultType.ERROR.name))
            .andExpect(jsonPath("$.data.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.data.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.data.data").value("사이즈는 1이상이어야 합니다."))
    }
}