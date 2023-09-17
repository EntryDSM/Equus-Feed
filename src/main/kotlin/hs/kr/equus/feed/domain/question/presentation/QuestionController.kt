package hs.kr.equus.feed.domain.question.presentation

import hs.kr.equus.feed.domain.question.presentation.dto.request.CreateQuestionRequest
import hs.kr.equus.feed.domain.question.service.CreateQuestionService
import hs.kr.equus.feed.global.exception.ExpiredTokenException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/question")
@RestController
class QuestionController(
    private val createQuestionService: CreateQuestionService
) {
    @PostMapping
    fun createQuestion(
        @RequestBody @Valid
        createQuestionRequest: CreateQuestionRequest
    ) {
        createQuestionService.execute(createQuestionRequest)
    }

    @GetMapping
    fun test() {
        throw ExpiredTokenException
    }
}
