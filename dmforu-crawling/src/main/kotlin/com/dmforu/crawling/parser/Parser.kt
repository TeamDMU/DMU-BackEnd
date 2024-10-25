package com.dmforu.crawling.parser

interface Parser<T> {
    fun parse(): List<T>
}