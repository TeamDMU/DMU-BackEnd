package com.dmforu.crawling.loader

interface HtmlLoader<Result> {
    fun get(url: String): Result
}