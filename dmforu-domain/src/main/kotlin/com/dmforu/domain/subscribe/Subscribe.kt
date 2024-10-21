package com.dmforu.domain.subscribe

class Subscribe private constructor(
    token: String,
    department: String,
    keywords: List<String>,
    isDepartmentSubscribed: Boolean,
    isKeywordSubscribed: Boolean
) {
    val token: String = token

    var department: String = department
        private set

    var keywords: List<String> = keywords
        private set

    var isDepartmentSubscribed: Boolean = isDepartmentSubscribed
        private set

    var isKeywordSubscribed: Boolean = isKeywordSubscribed
        private set

    companion object {
        fun of(
            token: String,
            department: String,
            keywords: List<String>,
            isDepartmentSubscribed: Boolean,
            isKeywordSubscribed: Boolean,
        ): Subscribe {
            return Subscribe(
                token = token,
                department = department,
                keywords = keywords,
                isDepartmentSubscribed = isDepartmentSubscribed,
                isKeywordSubscribed = isKeywordSubscribed
            )
        }
    }

    fun changeDepartment(department: String) {
        this.department = department
    }

    fun subscribeDepartment() {
        this.isDepartmentSubscribed = true
    }

    fun unsubscribeDepartment() {
        this.isDepartmentSubscribed = false
    }

    fun changeKeywords(keywords: List<String>) {
        this.keywords = keywords
    }

    fun subscribeKeyword() {
        this.isKeywordSubscribed = true
    }

    fun unsubscribeKeyword() {
        this.isKeywordSubscribed = false
    }

}