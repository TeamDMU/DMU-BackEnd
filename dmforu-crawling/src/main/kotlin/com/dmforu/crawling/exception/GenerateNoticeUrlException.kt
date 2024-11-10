package com.dmforu.crawling.exception

class GenerateNoticeUrlException(message: String = "공지 URL 생성에 실패했습니다.", cause: Throwable? = null) :
    RuntimeException(message, cause)