package com.dmforu.domain.subscribe

import com.dmforu.domain.Generated

class Subscribe private constructor(
    val token: String,
    department: String?,
    keywords: List<String>?,
    isDepartmentSubscribed: Boolean,
    isKeywordSubscribed: Boolean,
) {
    var department: String? = department
        private set

    var keywords: List<String>? = keywords
        private set

    var isDepartmentSubscribed: Boolean = isDepartmentSubscribed
        private set

    var isKeywordSubscribed: Boolean = isKeywordSubscribed
        private set

    companion object {
        fun of(
            token: String,
            department: String?,
            keywords: List<String>?,
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

    @Generated
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Subscribe

        if (token != other.token) return false
        if (department != other.department) return false
        if (keywords != other.keywords) return false
        if (isDepartmentSubscribed != other.isDepartmentSubscribed) return false
        if (isKeywordSubscribed != other.isKeywordSubscribed) return false

        return true
    }

    @Generated
    override fun hashCode(): Int {
        var result = token.hashCode()
        result = 31 * result + department.hashCode()
        result = 31 * result + keywords.hashCode()
        result = 31 * result + isDepartmentSubscribed.hashCode()
        result = 31 * result + isKeywordSubscribed.hashCode()
        return result
    }

    @Generated
    override fun toString(): String {
        return "Subscribe(token='$token', department='$department', keywords=$keywords, isDepartmentSubscribed=$isDepartmentSubscribed, isKeywordSubscribed=$isKeywordSubscribed)"
    }

}