package com.dmforu.storage.db.mongo

import com.dmforu.storage.db.mongo.config.MongoConfig
import com.dmforu.storage.db.mongo.diet.DietEntityRepositoryTest
import com.dmforu.storage.db.mongo.schedule.ScheduleEntityMongoRepositoryTest
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["spring.config.location = src/main/resources/mongo.yml"])
@SpringBootTest(classes = [ScheduleEntityMongoRepositoryTest::class, DietEntityRepositoryTest::class])
@SpringBootApplication
@Import(MongoConfig::class)
@ActiveProfiles("test")
class MongoTestSupport