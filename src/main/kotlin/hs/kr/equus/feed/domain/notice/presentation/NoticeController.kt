package hs.kr.equus.feed.domain.notice.presentation

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryNoticeResponse
import hs.kr.equus.feed.domain.notice.presentation.dto.request.UpdateNoticeRequest
import hs.kr.equus.feed.domain.notice.presentation.dto.response.GetNoticeResponse
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryNoticeTitleResponse
import hs.kr.equus.feed.domain.notice.service.*
import hs.kr.equus.feed.domain.notice.presentation.dto.response.UploadNoticeImageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService,
    private val uploadNoticeImageService: UploadNoticeImageService,
    private val updateNoticeService: UpdateNoticeService,
    private val queryNoticeTitleService: QueryNoticeTitleService,
    private val deleteNoticeService: DeleteNoticeService,
    private val queryNoticeListByTypeService: QueryNoticeListByTypeService,
    private val getNoticeService: GetNoticeService
) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createNotice(
        @RequestBody @Valid
        createNoticeRequest: CreateNoticeRequest
    ) {
        createNoticeService.execute(createNoticeRequest)
    }

    @PatchMapping("/{notice-id}")
    fun modifyNotice(
        @PathVariable(name = "notice-id") id: UUID,
        @RequestBody updateNoticeRequest: UpdateNoticeRequest
    ): ResponseEntity<String> =
        updateNoticeService.execute(id, updateNoticeRequest)

    @PostMapping("/image")
    fun uploadImage(
        @RequestPart(name = "photo") image: MultipartFile
    ): UploadNoticeImageResponse =
        uploadNoticeImageService.execute(image)

    @GetMapping("/title")
    fun queryTitle(): List<QueryNoticeTitleResponse> = queryNoticeTitleService.execute()

    @GetMapping("/{notice-id}")
    fun getNotice(
        @PathVariable(name = "notice-id", required = true)
        noticeId: UUID
    ): GetNoticeResponse = getNoticeService.execute(noticeId)

    @GetMapping
    fun getNoticeListByType(
        @RequestParam("type") type: NoticeType?
    ): QueryNoticeResponse =
        queryNoticeListByTypeService.execute(type)

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    fun deleteNotice(
        @PathVariable(name = "notice-id")id: UUID
    ) =
        deleteNoticeService.execute(id)
}
