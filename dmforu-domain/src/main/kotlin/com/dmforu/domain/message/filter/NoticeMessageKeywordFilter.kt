package com.dmforu.domain.message.filter

import com.dmforu.domain.message.Keywords

fun keywordFilter(title: String): String? {
    Keywords.entries.forEach {
        if (title.contains(it.korean)) {
            return it.korean
        }
    }

    val whiteSpaceRemovedTitle = title.replace(" ", "")
    if (whiteSpaceRemovedTitle.contains("중간고사")) {
        return "시험"
    }

    if (whiteSpaceRemovedTitle.contains("기말고사")) {
        return "시험"
    }

    return null
}