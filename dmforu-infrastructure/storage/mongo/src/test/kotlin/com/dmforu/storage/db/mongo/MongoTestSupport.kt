package com.dmforu.storage.db.mongo

import com.dmforu.storage.db.mongo.config.MongoConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["spring.config.location = src/main/resources/mongo.yml"])
@SpringBootTest(classes = [MongoApplicationTest::class])
@Import(MongoConfig::class)
@ActiveProfiles("test")
class MongoTestSupport