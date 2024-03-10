package hs.kr.equus.feed.domain.notice.presentation

import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.domain.notice.service.CreateNoticeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService
) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    fun createNotice(
        @RequestPart(name = "request")
        createNoticeRequest: CreateNoticeRequest,
        @RequestPart(name = "images", required = false)
        images: List<MultipartFile>?,
        @RequestPart(name = "files", required = false)
        files: List<MultipartFile>?
    ) {
        createNoticeService.execute(images, files, createNoticeRequest)
    }
}
