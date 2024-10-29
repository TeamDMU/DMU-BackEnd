package com.dmforu.storage.db.mongo.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(basePackages = ["com.dmforu.storage.db.mongo"])
internal class MongoConfig(
    @Value("\${spring.data.mongodb.uri}")
    val uri: String,

    @Value("\${spring.data.mongodb.database}")
    val database: String
) {


    @Bean
    fun mongoClient(): MongoClient {
        val pojoCodecRegistry: CodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build())
        val codecRegistry: CodecRegistry =
            fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry)
        return MongoClients.create(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(uri))
                .codecRegistry(codecRegistry)
                .build()
        )
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), database)
    }

}
