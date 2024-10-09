package com.dmforu.crawling.notice

abstract class UrlGenerator {
    protected abstract fun generateSearchUrl(): String
    protected abstract fun generateUrlFromSearch(url: String): String
}
