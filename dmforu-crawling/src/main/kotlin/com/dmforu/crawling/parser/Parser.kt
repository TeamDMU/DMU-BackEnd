package com.dmforu.crawling

interface Parser<T> {
    fun parse(): List<T>
}