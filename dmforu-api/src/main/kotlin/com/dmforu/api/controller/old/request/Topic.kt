package com.dmforu.api.controller.old.request

import com.dmforu.domain.Generated

@Generated
enum class Topic (val korean: String){
    exam("시험"),
    signup("수강"),
    speciallecture("특강"),
    seasonalsemester("계절학기"),
    leaveofabsence("휴학"),
    returntoschool("복학"),
    graduate("졸업"),
    switchmajors("전과"),
    givingupthesemester("학기포기"),
    scholarship("장학"),
    nationalscholarship("국가장학"),
    registration("등록금"),
    employment("채용"),
    contest("공모전"),
    competition("대회"),
    fieldtraining("현장실습"),
    volunteer("봉사"),
    dormitory("기숙사"),
    group("동아리"),
    studentcouncil("학생회"),
    overseastraining("해외연수"),
    reserveforces("예비군"),
    work("근로");
}