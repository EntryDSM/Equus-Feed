package hs.kr.equus.feed.domain.faq.presentation

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.domain.faq.presentation.dto.request.CreateFaqRequest
import hs.kr.equus.feed.domain.faq.service.CreateFaqService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/faq")
@RestController
class FaqPresentation(
    private val createFaqService: CreateFaqService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createFaq(@RequestBody @Validated createFaqRequest: CreateFaqRequest) = createFaqService.execute(createFaqRequest)
}
