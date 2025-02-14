package com.dmforu.api.controller.old

import com.dmforu.api.controller.v1.response.NoticeResponse
import com.dmforu.domain.notice.NoticeReader
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[구버전] 공지")
@RestController
class OldNoticeController(
    private val noticeReader: NoticeReader,
) {
    @Operation(
        summary = "[구버전] 대학 공지 API",
        description = "대학 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다."
    )
    @GetMapping("/api/v1/dmu/universityNotice")
    fun getOldUniversityNotice(
        @RequestParam(name = "page", defaultValue = "1") page: Int,
        @RequestParam(name = "size", defaultValue = "20") size: Int,
    ): ResponseEntity<List<NoticeResponse>> {
        val universityNotices = noticeReader.readUniversityNotice(page, size).map { NoticeResponse.form(it) }
        return ResponseEntity.ok().body(universityNotices)
    }

    @Operation(
        summary = "[구버전] 학과 공지 API",
        description = "해당하는 학과의 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다."
    )
    @GetMapping("/api/v1/dmu/departmentNotice/{department}")
    fun getOldDepartmentNotice(
        @PathVariable(name = "department") department: String,
        @RequestParam(name = "page", defaultValue = "1") page: Int,
        @RequestParam(name = "size", defaultValue = "20") size: Int,
    ): ResponseEntity<List<NoticeResponse>> {
        val checkDepartment = when (department) {
            "로봇공학과" -> {
                "로봇소프트웨어과"
            }
            "컴퓨터정보공학과" -> {
                "웹응용소프트웨어공학과"
            }
            else -> {
                department
            }
        }

        val departmentNotices = noticeReader.readDepartmentNotice(checkDepartment, page, size)
            .map { NoticeResponse.form(it) }
        return ResponseEntity.ok().body(departmentNotices)
    }

    @Operation(
        summary = "[구버전] 공지 검색 API",
        description = "해당하는 학과와 대학 공지에서 해당하는 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다."
    )
    @GetMapping("/api/v1/dmu/notice/{searchWord}")
    fun getOldNoticeByKeyword(
        @PathVariable(name = "searchWord") searchWord: String,
        @RequestParam(name = "department") department: String,
        @RequestParam(name = "page", defaultValue = "1") page: Int,
        @RequestParam(name = "size", defaultValue = "20") size: Int,
    ): ResponseEntity<List<NoticeResponse>> {
        val checkDepartment = when (department) {
            "로봇공학과" -> {
                "로봇소프트웨어과"
            }
            "컴퓨터정보공학과" -> {
                "웹응용소프트웨어공학과"
            }
            else -> {
                department
            }
        }

        val notices = noticeReader.searchNotice(searchWord, checkDepartment, page, size).map { NoticeResponse.form(it) }
        return ResponseEntity.ok().body(notices)
    }
}