package com.dmforu.domain.subscribe

data class Subscribe(
    val token: String,
    var department: String,
    var keywords: List<String>,
    var isDepartmentSubscribed: Boolean,
    var areKeywordsSubscribed: Boolean,
) {
    fun subscribeDepartment() {
        this.isDepartmentSubscribed = true;
    }

    fun unsubscribeDepartment() {
        this.isDepartmentSubscribed = false;
    }

    fun subscribeKeywords() {
        this.areKeywordsSubscribed = true;
    }

    fun unsubscribeKeywords() {
        this.areKeywordsSubscribed = false;
    }
}