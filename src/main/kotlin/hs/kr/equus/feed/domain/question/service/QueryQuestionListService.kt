package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionDTO
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionListResponse
import hs.kr.equus.feed.infrastructure.feign.client.user.UserFeignClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryQuestionListService(
    private val questionRepository: QuestionRepository,
    private val userFeignClient: UserFeignClient
) {
    @Transactional(readOnly = true)
    fun execute() : QuestionListResponse {
        val questions = questionRepository.findAll().map { it ->
            QuestionDTO(
                id = it.id,
                title = it.title,
                createdAt = it.createdAt,
                username = userFeignClient.getUserByUUID(it.userId).name,
                isReplied = it.isReplied,
                isPublic = it.isPublic
            )
        }
        return QuestionListResponse(questions = questions)
    }
}
