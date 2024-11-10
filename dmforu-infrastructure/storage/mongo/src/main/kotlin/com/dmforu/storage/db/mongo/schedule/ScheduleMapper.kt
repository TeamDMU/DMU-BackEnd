package com.dmforu.storage.db.mongo.schedule

import com.dmforu.domain.schedule.Schedule
import com.dmforu.storage.db.mongo.schedule.entity.MonthEntity
import com.dmforu.storage.db.mongo.schedule.entity.ScheduleEntity
import com.dmforu.storage.db.mongo.schedule.entity.SchedulesEntity
import com.dmforu.storage.db.mongo.schedule.entity.YearEntity

internal object ScheduleMapper {
    fun mapToEntity(schedules: List<Schedule.Year>): SchedulesEntity {
        val result = schedules.map { toYearEntity(it) }
        return SchedulesEntity(
            years = result
        )
    }

    fun entityToSchedules(entity: SchedulesEntity?): List<Schedule.Year>? {
        return entity?.years?.map { toYearSchedule(it) }
    }

    private fun toYearEntity(yearSchedule: Schedule.Year): YearEntity {
        val result = yearSchedule.yearSchedule.map {
            toMonthEntity(it)
        }

        return YearEntity(
            year = yearSchedule.year,
            yearSchedule = result
        )
    }

    private fun toMonthEntity(monthSchedule: Schedule.Month): MonthEntity {
        val result = monthSchedule.monthSchedule.map {
            toScheduleEntity(it)
        }

        return MonthEntity(
            month = monthSchedule.month,
            monthSchedule = result
        )
    }

    private fun toScheduleEntity(schedule: Schedule): ScheduleEntity {
        return ScheduleEntity(
            dates = schedule.dates,
            content = schedule.content
        )
    }

    private fun toYearSchedule(yearEntity: YearEntity): Schedule.Year {
        val monthSchedules = yearEntity.yearSchedule.map { toMonthSchedule(it) }
        return Schedule.Year.of(yearEntity.year, monthSchedules)
    }

    private fun toMonthSchedule(monthEntity: MonthEntity): Schedule.Month {
        val schedules = monthEntity.monthSchedule.map { toSchedule(it) }
        return Schedule.Month.of(monthEntity.month, schedules)
    }

    private fun toSchedule(scheduleEntity: ScheduleEntity): Schedule {
        return Schedule.of(scheduleEntity.dates, scheduleEntity.content)
    }
}