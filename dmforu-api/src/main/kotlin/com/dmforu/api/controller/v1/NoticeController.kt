package com.dmforu.api.controller.v1

import com.dmforu.api.controller.v1.request.PaginationRequest
import com.dmforu.api.controller.v1.response.NoticeResponse
import com.dmforu.api.support.response.ApiResponse
import com.dmforu.api.support.response.SuccessResponse
import com.dmforu.domain.notice.NoticeReader
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "공지")
@RestController
class NoticeController(
    private val noticeReader: NoticeReader,
) {
    @Operation(
        summary = "대학 공지 API",
        description = "대학 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다."
    )
    @GetMapping("/api/v1/notice/university")
    fun getUniversityNotice(
        @Valid @ModelAttribute paginationRequest: PaginationRequest,
    ): ApiResponse<List<NoticeResponse>> {
        val universityNotices =
            noticeReader.readUniversityNotice(
                page = paginationRequest.page,
                size = paginationRequest.size
            ).map { NoticeResponse.form(it) }
        return SuccessResponse.success(universityNotices)
    }

    @Operation(
        summary = "학과 공지 API",
        description = "해당하는 학과의 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다."
    )
    @GetMapping("/api/v1/notice/department")
    fun getDepartmentNotice(
        @RequestParam(name = "department") department: String,
        @Valid @ModelAttribute paginationRequest: PaginationRequest,
    ): ApiResponse<List<NoticeResponse>> {
        val departmentNotices =
            noticeReader.readDepartmentNotice(
                department = department,
                page = paginationRequest.page,
                size = paginationRequest.size
            ).map { NoticeResponse.form(it) }
        return SuccessResponse.success(departmentNotices)
    }

    @Operation(
        summary = "공지 검색 API",
        description = "해당하는 학과와 대학 공지에서 해당하는 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다."
    )
    @GetMapping("/api/v1/notice/{searchWord}")
    fun getNoticeByKeyword(
        @PathVariable(name = "searchWord") searchWord: String,
        @RequestParam(name = "department") department: String,
        @Valid @ModelAttribute paginationRequest: PaginationRequest,
    ): ApiResponse<List<NoticeResponse>> {
        val notices = noticeReader.searchNotice(
            searchWord = searchWord,
            department = department,
            page = paginationRequest.page,
            size = paginationRequest.size
        ).map { NoticeResponse.form(it) }
        return SuccessResponse.success(notices)
    }
}