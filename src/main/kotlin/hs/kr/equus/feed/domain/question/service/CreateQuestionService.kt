package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.Question
import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.presentation.dto.request.CreateQuestionRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CreateQuestionService(
    private val questionRepository: QuestionRepository,
    private val userUtils: UserUtils
) {
    @Transactional
    fun execute(createQuestionRequest: CreateQuestionRequest) {
        val question = Question(
            id = null,
            title = createQuestionRequest.title,
            content = createQuestionRequest.content,
            isPublic = createQuestionRequest.isPublic,
            isReplied = false,
            userId = UUID.fromString(userUtils.getCurrentUserId())
        )
        questionRepository.save(question)
    }
}
