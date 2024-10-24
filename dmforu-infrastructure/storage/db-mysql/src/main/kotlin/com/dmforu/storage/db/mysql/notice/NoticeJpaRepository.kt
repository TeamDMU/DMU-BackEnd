package com.dmforu.storage.db.mysql.notice

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

internal interface NoticeJpaRepository : JpaRepository<NoticeEntity, Long> {
    /** 원하는 타입의 가장 최신 공지사항 번호를 확인하는 메서드
     *
     * @Param type (학과 이름 또는 대학)
     * @Return type에 알맞는 최신 공지사항 번호, 만약 공지사항이 존재하지 않다면 Null을 반환한다.
     */
    @Query("SELECT MAX(e.number) FROM NoticeEntity e WHERE e.type = :type")
    fun findMaxNumberByType(@Param("type") type: String): Int?

    /**
     * 원하는 타입의 공지사항을 페이징네이션하는 메서드
     *
     * @param type      공지사항 타입 (학과 이름 또는 대학)
     * @param pageable  페이지 단위
     * @return 학과의 공지사항을 페이지 단위에 맞게 반환한다.
     */
    fun findByType(type: String, pageable: Pageable): Page<NoticeEntity>

    /**
     * 학과, 대학 공지사항을 검색하는 메서드 <br></br>
     * 페이지네이션을 적용하여 페이지 단위에 알맞게 반환한다.
     *
     * @param searchWord  검색할 키워드
     * @param pageable    페이지 단위
     * @return 키워드에 맞는 공지사항 페이지
     */
    @Query(
        value = "SELECT * FROM notice WHERE REPLACE(title, ' ', '') LIKE CONCAT('%', REPLACE(?1, ' ', ''), '%') AND type IN (?2, '대학')",
        nativeQuery = true
    )
    fun findBySearchWordAndDepartment(searchWord: String, department: String, pageable: Pageable): Page<NoticeEntity>

}