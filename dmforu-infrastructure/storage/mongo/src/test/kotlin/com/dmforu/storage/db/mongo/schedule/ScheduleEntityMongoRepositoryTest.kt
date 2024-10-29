package com.dmforu.storage.db.mongo.schedule


import com.dmforu.domain.schedule.Schedule
import com.dmforu.storage.db.mongo.MongoApplicationTest
import com.dmforu.storage.db.mongo.config.MongoConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import kotlin.test.Test

@TestPropertySource(properties = ["spring.config.location = src/main/resources/mongo.yml"])
@SpringBootTest(classes = [MongoApplicationTest::class])
@Import(MongoConfig::class)
class ScheduleEntityMongoRepositoryTest {

    @Autowired
    private lateinit var mongoRepository: ScheduleMongoRepository

    @Autowired
    private lateinit var entityRepository: ScheduleEntityRepository

    @AfterEach
    fun tearDown() {
        mongoRepository.deleteAll()
    }

    @DisplayName("학사 일정을 저장한다.")
    @Test
    fun write() {
        // given
        val schedule = Schedule.of(listOf("날짜1", "날짜2"), "내용")
        val monthSchedule = Schedule.Month.of(1, listOf(schedule))
        val yearSchedule = Schedule.Year.of(1, listOf(monthSchedule))
        val schedules = listOf(yearSchedule, yearSchedule)

        // when
        entityRepository.write(schedules)

        // then
        val result = mongoRepository.findAll()[0]

        assertThat(result).isNotNull
        assertThat(ScheduleMapper.entityToSchedules(result)).isEqualTo(schedules)
    }

    @DisplayName("학사 일정을 불러온다.")
    @Test
    fun read() {
        // given
        val schedule = Schedule.of(listOf("날짜1", "날짜2"), "내용")
        val monthSchedule = Schedule.Month.of(1, listOf(schedule))
        val yearSchedule = Schedule.Year.of(1, listOf(monthSchedule))
        val schedules = listOf(yearSchedule, yearSchedule)

        mongoRepository.save(ScheduleMapper.mapToEntity(schedules))

        // when
        val savedEntity = entityRepository.read()

        assertThat(savedEntity).isNotNull
        assertThat(savedEntity).isEqualTo(schedules)
    }

    @DisplayName("학사 일정을 불러올 때, 학사 일정이 없다면 null을 반환한다.")
    @Test
    fun readWhenEmpty() {
        // given

        // when
        val savedEntity = entityRepository.read()

        // then
        assertThat(savedEntity).isNull()
    }
}