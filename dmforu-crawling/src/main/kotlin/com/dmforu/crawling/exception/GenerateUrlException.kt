package com.dmforu.crawling.exception

class GenerateUrlException(message: String = "URL 생성에 실패했습니다.", cause: Throwable? = null) :
    RuntimeException(message, cause)