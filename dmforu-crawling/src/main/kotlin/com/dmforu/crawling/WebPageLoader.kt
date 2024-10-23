package com.dmforu.crawling

interface WebPageLoader<Result> {
    fun getHTML(url: String): Result
}