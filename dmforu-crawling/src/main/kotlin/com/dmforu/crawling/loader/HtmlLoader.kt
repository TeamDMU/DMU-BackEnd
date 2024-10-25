package com.dmforu.crawling

interface HtmlLoader<Result> {
    fun getHTML(url: String): Result
}