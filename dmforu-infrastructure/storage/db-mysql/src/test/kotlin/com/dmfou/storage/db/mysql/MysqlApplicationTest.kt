package com.dmfou.storage.db.mysql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackages = ["com.dmforu.storage.db.mysql"]
)
class MysqlApplicationTest