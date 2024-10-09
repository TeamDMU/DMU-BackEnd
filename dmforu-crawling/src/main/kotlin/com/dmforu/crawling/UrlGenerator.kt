package com.dmforu.crawling

abstract class UrlGenerator {
    protected abstract fun generateSearchUrl(): String
    protected abstract fun generateUrlFromSearch(url: String): String
}
