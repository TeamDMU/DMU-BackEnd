package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.storage.db.mysql.converter.StringListConverter
import jakarta.persistence.*


@Table(name = "token")
@Entity
internal class SubscribeEntity(
    @Id
    val token: String,

    @Column(nullable = true)
    val department: String?,

    @Convert(converter = StringListConverter::class)
    @Column(name = "keywords_list", nullable = true)
    val keywords: List<String>?,

    @Column(name = "department_onoff")
    val isDepartmentSubscribed: Boolean,

    @Column(name = "keyword_onoff")
    val isKeywordSubscribed: Boolean,
) {
    protected constructor() : this("", "", emptyList(), false, false)

    companion object {
        fun from(subscribe: Subscribe): SubscribeEntity {
            return SubscribeEntity(
                token = subscribe.token,
                department = subscribe.department,
                keywords = subscribe.keywords,
                isDepartmentSubscribed = subscribe.isDepartmentSubscribed,
                isKeywordSubscribed = subscribe.isKeywordSubscribed,
            )
        }
    }

    fun toSubscribe(): Subscribe {
        return Subscribe.of(
            token = token,
            department = department,
            keywords = keywords,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = isKeywordSubscribed
        )
    }

}