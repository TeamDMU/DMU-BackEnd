package com.dmforu.storage.db.mongo.diet

import com.dmforu.storage.db.mongo.diet.entity.DietsEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
internal interface DietMongoRepository : MongoRepository<DietsEntity, String> {}