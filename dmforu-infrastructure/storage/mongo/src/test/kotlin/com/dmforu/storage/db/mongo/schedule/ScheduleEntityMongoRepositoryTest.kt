package com.dmforu.storage.db.mongo.schedule


import com.dmforu.domain.schedule.Schedule
import com.dmforu.storage.db.mongo.MongoTestSupport
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

class ScheduleEntityMongoRepositoryTest : MongoTestSupport(){

    @Autowired
    private lateinit var scheduleMongoRepository: ScheduleMongoRepository

    @Autowired
    private lateinit var scheduleEntityRepository: ScheduleEntityRepository

    @AfterEach
    fun tearDown() {
        scheduleMongoRepository.deleteAll()
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
        scheduleEntityRepository.write(schedules)

        // then
        val result = scheduleMongoRepository.findAll()[0]

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

        scheduleMongoRepository.save(ScheduleMapper.mapToEntity(schedules))

        // when
        val savedEntity = scheduleEntityRepository.read()

        assertThat(savedEntity).isNotNull
        assertThat(savedEntity).isEqualTo(schedules)
    }

    @DisplayName("학사 일정을 불러올 때, 학사 일정이 없다면 null을 반환한다.")
    @Test
    fun readWhenEmpty() {
        // given

        // when
        val savedEntity = scheduleEntityRepository.read()

        // then
        assertThat(savedEntity).isNull()
    }
}