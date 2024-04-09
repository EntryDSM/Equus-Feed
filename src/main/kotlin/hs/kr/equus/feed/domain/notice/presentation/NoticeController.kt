package hs.kr.equus.feed.domain.notice.presentation

import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.domain.notice.service.CreateNoticeService
import hs.kr.equus.feed.domain.notice.service.QueryNoticeTitleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService,
    private val queryNoticeTitleService: QueryNoticeTitleService
) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createNotice(
        @RequestPart(name = "request") @Valid
        createNoticeRequest: CreateNoticeRequest,
        @RequestPart(name = "file", required = false)
        files: List<MultipartFile>?
    ) {
        createNoticeService.execute(files, createNoticeRequest)
    }

    @GetMapping("/title")
    fun queryTitle() =
        queryNoticeTitleService.execute()
}
