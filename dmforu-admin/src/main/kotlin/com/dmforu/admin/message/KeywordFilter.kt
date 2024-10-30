package com.dmforu.admin.message

import com.dmforu.domain.message.Keywords
import org.springframework.stereotype.Component

@Component
class KeywordFilter {

    fun extractKeywordFrom(title: String): String? {
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

}