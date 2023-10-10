package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionDTO
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionListResponse
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.feign.client.user.UserFeignClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyQuestionService(
    private val userUtils: UserUtils,
    private val questionRepository: QuestionRepository,
    private val userFeignClient: UserFeignClient
) {
    @Transactional(readOnly = true)
    fun execute(): QuestionListResponse {
        val userId = userUtils.getCurrentUserId()
        val questionList = questionRepository.findAllByUserId(userId).map { it ->
            QuestionDTO(
                id = it.id!!,
                title = it.title,
                createdAt = it.createdAt,
                username = userFeignClient.getUserByUUID(it.userId).name,
                isReplied = it.isReplied,
                isPublic = it.isPublic
            )
        }
        return QuestionListResponse(questionList)
    }
}
