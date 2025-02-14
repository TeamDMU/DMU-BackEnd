package com.dmforu.crawling.parser

enum class DepartmentCrawlingPath (val type: String, val majorPath: String, val noticePath: String) {
    // 기계공학부
    MECHANICAL_ENGINEERING_DEPARTMENT("기계공학과", "dmu_23205", "1707"),
    MECHANICAL_DESIGN_ENGINEERING("기계설계공학과", "dmu_23207", "1716"),

    // 로봇자동화공학부
    AUTOMATION_ENGINEERING("자동화공학과", "dmu_23209", "1728"),
    ROBOT_SOFTWARE_ENGINEERING("로봇소프트웨어과", "dmu_23211", "1737"),

    // 전기전자통신공학부
    ELECTRICAL_ENGINEERING("전기공학과", "dmu_23212", "1749"),
    INFORMATION_ELECTRONIC_ENGINEERING("정보전자공학과", "dmu_23214", "1758"),
    SEMICONDUCTOR_ELECTRONIC_ENGINEERING("반도체전자공학과", "dmu_23216", "1767"),
    INFORMATION_COMMUNICATION_ENGINEERING("정보통신공학과", "dmu_23218", "1776"),
    FIRE_SAFETY_MANAGEMENT("소방안전관리과", "dmu_23268", "2503"),

    // 컴퓨터공학부
    WEB_APPLIED_SOFTWARE_ENGINEERING("웹응용소프트웨어공학과", "dmu_23220", "1788"),
    COMPUTER_SOFTWARE_ENGINEERING("컴퓨터소프트웨어공학과", "dmu_23222", "1797"),
    AI_SOFTWARE_ENGINEERING("인공지능소프트웨어학과", "dmu_23259", "1806"),

    // 생활환경공학부
    BIO_CHEMICAL_ENGINEERING("생명화학공학과", "dmu_23245", "1818"),
    BIO_CONVERGENCE_ENGINEERING("바이오융합공학과", "dmu_23264", "1827"),
    ARCHITECTURE("건축과", "dmu_23015", "1836"),
    INTERIOR_ARCHITECTURE_DESIGN("실내건축디자인과", "dmu_23256", "1845"),
    VISUAL_DESIGN("시각디자인과", "dmu_23068", "1854"),
    AR_VR_CONTENTS_DESIGN("AR-VR콘텐츠디자인과", "dmu_23269", "2633"),

    // 경영학부
    BUSINESS_MANAGEMENT("경영학과", "dmu_23232", "1866"),
    TAX_ACCOUNTING("세무회계학과", "dmu_23234", "1875"),
    DISTRIBUTION_MARKETING("유통마케팅학과", "dmu_23236", "1884"),
    HOTEL_TOURISM("호텔관광학과", "dmu_23250", "1893"),
    BUSINESS_INFORMATION("경영정보학과", "dmu_23238", "1902"),
    BIG_DATA_MANAGEMENT("빅데이터경영과", "dmu_23260", "1911"),

    // 자유전공학과 https://www.dongyang.ac.kr/dmu_23274/3071/subview.do
    UNDECLARED_MAJOR("자유전공학과", "dmu_23274", "3071");
}