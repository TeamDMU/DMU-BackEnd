package com.dmforu.domain.schedule

class ScheduleReader(
    private val scheduleRepository: ScheduleRepository
) {
    // TODO
    fun read(): List<Schedule.Year> {
        return scheduleRepository.read() ?: throw IllegalArgumentException("No schedule exists 관리자 에러")
    }
}