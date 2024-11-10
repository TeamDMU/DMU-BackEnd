package com.dmforu.api.controller.old

import com.dmforu.api.ControllerTestSupport
import com.dmforu.api.support.error.ErrorCode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OldNoticeControllerTest : ControllerTestSupport() {

    @DisplayName("대학 공지를 불러온다.")
    @Test
    fun getUniversityNotice() {
        // given
        given(noticeReader.readUniversityNotice(anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/dmu/notice/universityNotice")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)
    }

    @DisplayName("학과 공지를 불러온다.")
    @Test
    fun getDepartmentNotice() {
        // given
        given(noticeReader.readDepartmentNotice(anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/dmu/departmentNotice/{department}", "컴퓨터소프트웨어공학과")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)
    }

    @DisplayName("학과 공지를 불러올 때, 학과는 필수 값이다.")
    @Test
    fun getDepartmentNoticeWhenDepartmentIsEmpty() {
        mockMvc.perform(
            get("/api/v1/dmu/departmentNotice/")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E404.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 URL로 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").isEmpty)
    }


    @DisplayName("공지를 검색한다.")
    @Test
    fun getNoticeByKeyword() {
        // given
        given(noticeReader.searchNotice(anyString(), anyString(), anyInt(), anyInt())).willReturn(listOf())

        // when then
        mockMvc.perform(
            get("/api/v1/dmu/notice/{searchWord}", "검색어")
                .param("department", "컴퓨터소프트웨어공학과")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)
    }

    @DisplayName("공지를 검색할 때, 학과는 필수 값이다.")
    @Test
    fun getNoticeByKeywordWhen() {
        mockMvc.perform(
            get("/api/v1/dmu/notice/{searchWord}", "검색어")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E400.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").isEmpty)
    }

    @DisplayName("공지를 검색할 때, 검색어는 필수 값이다. ")
    @Test
    fun getNoticeByKeywordWhenPathValueIsEmpty() {
        mockMvc.perform(
            get("/api/v1/dmu/notice/")
                .param("department", "컴퓨터소프트웨어공학과")
                .param("page", "1")
                .param("size", "20")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.error.code").value(ErrorCode.E404.name))
            .andExpect(jsonPath("$.error.message").value("잘못된 URL로 요청을 하였습니다."))
            .andExpect(jsonPath("$.error.data").isEmpty)
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
            get("/api/v1/dmu/notice/universityNotice")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)

        mockMvc.perform(
            get("/api/v1/dmu/notice/departmentNotice")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)

        mockMvc.perform(
            get("/api/v1/dmu/notice/{searchWord}", "검색어")
                .param("department", "컴퓨터소프트웨어공학과")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray)
    }

}