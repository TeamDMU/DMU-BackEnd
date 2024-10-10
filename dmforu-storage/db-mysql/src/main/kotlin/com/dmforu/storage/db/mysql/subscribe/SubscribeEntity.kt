package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.storage.db.mysql.converter.StringListConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Table(name = "token")
@Entity
internal class SubscribeEntity(
    @Id
    val token: String,

    val department: String,

    @Convert(converter = StringListConverter::class)
    val keywords: List<String>?,

    val isDepartmentSubscribed: Boolean,

    val areKeywordsSubscribed: Boolean,
) {
    constructor() : this("", "", emptyList(), false, false)

    companion object {
        fun from(subscribe: Subscribe): SubscribeEntity {
            return SubscribeEntity(
                token = subscribe.token,
                department = subscribe.department,
                keywords = subscribe.keywords,
                isDepartmentSubscribed = false,
                areKeywordsSubscribed = false
            )
        }
    }
}