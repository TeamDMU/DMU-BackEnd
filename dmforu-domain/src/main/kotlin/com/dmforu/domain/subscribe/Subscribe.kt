package com.dmforu.domain.subscribe

data class Subscribe(
    val token: String,
    var department: String,
    var keywords: List<String>,
    var isDepartmentSubscribed: Boolean,
    var isKeywordSubscribed: Boolean,
) {
    fun subscribeDepartment() {
        this.isDepartmentSubscribed = true;
    }

    fun unsubscribeDepartment() {
        this.isDepartmentSubscribed = false;
    }

    fun subscribeKeyword() {
        this.isKeywordSubscribed = true;
    }

    fun unsubscribeKeyword() {
        this.isKeywordSubscribed = false;
    }
}