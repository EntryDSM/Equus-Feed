package hs.kr.equus.feed.domain.question.domain.repository

import hs.kr.equus.feed.domain.question.domain.Question
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface QuestionRepository : JpaRepository<Question, UUID> {
    fun findAllByUserId(userId: UUID): List<Question>
    fun deleteAllByUserId(userId: UUID): Question
}
