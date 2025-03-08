package com.dmforu.crawling.parser

enum class DepartmentCrawlingPath(val type: String, val path: String) {
    // 기계공학부
    MECHANICAL_ENGINEERING_DEPARTMENT("기계공학과", "4461"),
    MECHANICAL_DESIGN_ENGINEERING("기계설계공학과", "4474"),

    // 로봇자동화공학부
    AUTOMATION_ENGINEERING("자동화공학과", "4487"),
    ROBOT_SOFTWARE_ENGINEERING("로봇소프트웨어과", "4502"),

    // 전기전자통신공학부
    ELECTRICAL_ENGINEERING("전기공학과", "4518"),
    INFORMATION_ELECTRONIC_ENGINEERING("정보전자공학과", "4530"),
    SEMICONDUCTOR_ELECTRONIC_ENGINEERING("반도체전자공학과", "4530"),
    INFORMATION_COMMUNICATION_ENGINEERING("정보통신공학과", "4543"),
    FIRE_SAFETY_MANAGEMENT("소방안전관리과", "4557"),

    // 컴퓨터공학부
    WEB_APPLIED_SOFTWARE_ENGINEERING("웹응용소프트웨어공학과", "4568"),
    COMPUTER_SOFTWARE_ENGINEERING("컴퓨터소프트웨어공학과", "4580"),
    AI_SOFTWARE_ENGINEERING("인공지능소프트웨어학과", "4593"),

    // 생활환경공학부
    BIO_CHEMICAL_ENGINEERING("생명화학공학과", "4605"),
    BIO_CONVERGENCE_ENGINEERING("바이오융합공학과", "4617"),
    ARCHITECTURE("건축과", "4629"),
    INTERIOR_ARCHITECTURE_DESIGN("실내건축디자인과", "4643"),
    VISUAL_DESIGN("시각디자인과", "4654"),
    AR_VR_CONTENTS_DESIGN("AR-VR콘텐츠디자인과", "4666"),

    // 경영학부
    BUSINESS_MANAGEMENT("경영학과", "4677"),
    TAX_ACCOUNTING("세무회계학과", "4687"),
    DISTRIBUTION_MARKETING("유통마케팅학과", "4697"),
    HOTEL_TOURISM("호텔관광학과", "4708"),
    BUSINESS_INFORMATION("경영정보학과", "4719"),
    BIG_DATA_MANAGEMENT("빅데이터경영과", "4729"),

    // 자유전공학과
    UNDECLARED_MAJOR("자유전공학과", "4739");
}