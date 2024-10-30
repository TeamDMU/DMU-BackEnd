package com.dmforu.admin.message

import com.dmforu.domain.message.Keywords
import org.springframework.stereotype.Component

@Component
class KeywordFilter {

    fun extractKeywordFrom(title: String): String? {
        val whiteSpaceRemovedTitle = title.replace(" ", "")

        Keywords.entries.forEach {
            if (whiteSpaceRemovedTitle.contains(it.korean)) {
                return it.korean
            }
        }

        if (whiteSpaceRemovedTitle.contains("중간고사")) {
            return "시험"
        }

        if (whiteSpaceRemovedTitle.contains("기말고사")) {
            return "시험"
        }

        return null
    }

}