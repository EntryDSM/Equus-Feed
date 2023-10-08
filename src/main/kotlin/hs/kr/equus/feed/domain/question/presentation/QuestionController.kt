package hs.kr.equus.feed.domain.question.presentation

import hs.kr.equus.feed.domain.question.presentation.dto.request.CreateQuestionRequest
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionDetailsResponse
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionListResponse
import hs.kr.equus.feed.domain.question.service.CreateQuestionService
import hs.kr.equus.feed.domain.question.service.QueryQuestionDetailsService
import hs.kr.equus.feed.domain.question.service.QueryQuestionListService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/question")
@RestController
class QuestionController(
    private val createQuestionService: CreateQuestionService,
    private val queryQuestionListService: QueryQuestionListService,
    private val queryQuestionDetailsService: QueryQuestionDetailsService
) {
    @PostMapping
    fun createQuestion(
        @RequestBody @Valid
        createQuestionRequest: CreateQuestionRequest
    ) {
        createQuestionService.execute(createQuestionRequest)
    }

    @GetMapping("/all")
    fun getQuestionList(): QuestionListResponse = queryQuestionListService.execute()

    @GetMapping("/{questionId}")
    fun getQuestionDetails(@PathVariable("questionId") questionId: UUID): QuestionDetailsResponse =
        queryQuestionDetailsService.execute(questionId)
}
