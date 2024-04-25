package hs.kr.equus.feed.domain.question.presentation

import hs.kr.equus.feed.domain.question.presentation.dto.request.CreateQuestionRequest
import hs.kr.equus.feed.domain.question.presentation.dto.request.UpdateQuestionRequest
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionDetailsResponse
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionListResponse
import hs.kr.equus.feed.domain.question.service.CreateQuestionService
import hs.kr.equus.feed.domain.question.service.QueryMyQuestionService
import hs.kr.equus.feed.domain.question.service.QueryQuestionDetailsService
import hs.kr.equus.feed.domain.question.service.QueryQuestionListService
import hs.kr.equus.feed.domain.question.service.UpdateQuestionService
import hs.kr.equus.feed.domain.question.service.DeleteQuestionService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PatchMapping
import java.util.UUID

@RequestMapping("/question")
@RestController
class QuestionController(
    private val createQuestionService: CreateQuestionService,
    private val queryQuestionListService: QueryQuestionListService,
    private val queryQuestionDetailsService: QueryQuestionDetailsService,
    private val updateQuestionService: UpdateQuestionService,
    private val queryMyQuestionService: QueryMyQuestionService,
    private val deleteQuestionService: DeleteQuestionService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createQuestion(
        @RequestBody @Validated
        createQuestionRequest: CreateQuestionRequest
    ) {
        createQuestionService.execute(createQuestionRequest)
    }

    @GetMapping("/all")
    fun getQuestionList(@PageableDefault(size = 10) pageable: Pageable): QuestionListResponse = queryQuestionListService.execute(pageable)

    @GetMapping("/{questionId}")
    fun getQuestionDetails(@PathVariable("questionId") questionId: UUID): QuestionDetailsResponse =
        queryQuestionDetailsService.execute(questionId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{questionId}")
    fun updateQuestion(
        @PathVariable questionId: UUID,
        @Validated @RequestBody
        updateQuestionRequest: UpdateQuestionRequest
    ) = updateQuestionService.execute(questionId, updateQuestionRequest)

    @GetMapping
    fun getMyQuestionList(): QuestionListResponse = queryMyQuestionService.execute()

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{questionId}")
    fun deleteQuestion(@PathVariable("questionId") questionId: UUID) = deleteQuestionService.execute(questionId)
}
