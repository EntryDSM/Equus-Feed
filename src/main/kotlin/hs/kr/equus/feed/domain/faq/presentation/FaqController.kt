package hs.kr.equus.feed.domain.faq.presentation

import hs.kr.equus.feed.domain.faq.domain.type.FaqType
import hs.kr.equus.feed.domain.faq.presentation.dto.request.CreateFaqRequest
import hs.kr.equus.feed.domain.faq.presentation.dto.request.UpdateFaqRequest
import hs.kr.equus.feed.domain.faq.presentation.dto.response.FaqDetailsResponse
import hs.kr.equus.feed.domain.faq.presentation.dto.response.FaqListResponse
import hs.kr.equus.feed.domain.faq.service.*
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/faq")
@RestController
class FaqController(
    private val createFaqService: CreateFaqService,
    private val queryFaqDetailsService: QueryFaqDetailsService,
    private val queryFaqListByTypeService: QueryFaqListByTypeService,
    private val queryFaqListService: QueryFaqListService,
    private val updateFaqService: UpdateFaqService,
    private val deleteFaqService: DeleteFaqService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createFaq(
        @RequestBody @Validated
        createFaqRequest: CreateFaqRequest
    ) = createFaqService.execute(createFaqRequest)

    @GetMapping("/{faq-id}")
    fun queryFaqDetails(@PathVariable("faq-id") faqId: UUID): FaqDetailsResponse =
        queryFaqDetailsService.execute(faqId)

    @GetMapping
    fun queryFaqListByType(@RequestParam("type") faqType: FaqType): FaqListResponse =
        queryFaqListByTypeService.execute(faqType)

    @GetMapping("/all")
    fun queryFaqList(): FaqListResponse = queryFaqListService.execute()

    @PatchMapping("/{faq-id}")
    fun updateFaq(
        @PathVariable("faq-id") faqId: UUID,
        @RequestBody @Validated
        updateFaqRequest: UpdateFaqRequest
    ) =
        updateFaqService.execute(faqId, updateFaqRequest)

    @DeleteMapping("/{faq-id}")
    fun deleteFaq(@PathVariable("faq-id") faqId: UUID) = deleteFaqService.execute(faqId)
}
