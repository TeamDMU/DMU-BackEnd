package com.dmforu.api.controller.old.request

import com.dmforu.domain.Generated

@Generated
enum class Topic (val korean: String){
    exam("시험"),
    sign_up("수강"),
    special_lecture("특강"),
    seasonal_semester("계절학기"),
    leave_of_absence("휴학"),
    return_to_school("복학"),
    graduate("졸업"),
    switch_majors("전과"),
    giving_up_the_semester("학기포기"),
    scholarship("장학"),
    national_scholarship("국가장학"),
    registration("등록금"),
    employment("채용"),
    contest("공모전"),
    competition("대회"),
    field_training("현장실습"),
    volunteer("봉사"),
    dormitory("기숙사"),
    group("동아리"),
    student_council("학생회"),
    overseas_training("해외연수"),
    reserve_forces("예비군"),
    work("근로");
}