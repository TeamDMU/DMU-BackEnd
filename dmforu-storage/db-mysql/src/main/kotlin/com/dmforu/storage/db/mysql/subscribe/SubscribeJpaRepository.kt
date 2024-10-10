package com.dmforu.storage.db.mysql.subscribe

import org.springframework.data.jpa.repository.JpaRepository

internal interface SubscribeJpaRepository : JpaRepository<SubscribeEntity, String> {

}