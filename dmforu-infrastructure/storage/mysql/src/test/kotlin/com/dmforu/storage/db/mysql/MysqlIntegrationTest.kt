package com.dmforu.storage.db.mysql

import com.dmforu.storage.db.mysql.config.MysqlJpaConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [MysqlApplicationTest::class])
@Import(MysqlJpaConfig::class)
abstract class MysqlIntegrationTest {}