package hs.kr.equus.feed.domain.notice.presentation

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.domain.notice.presentation.dto.request.ModifyNoticeRequest
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryNoticeResponse
import hs.kr.equus.feed.domain.notice.service.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService,
    private val uploadNoticeImageService: UploadNoticeImageService,
    private val modifyNoticeService: ModifyNoticeService,
    private val queryNoticeTitleService: QueryNoticeTitleService,
    private val deleteNoticeService: DeleteNoticeService,
    private val queryNoticeListByTypeService: QueryNoticeListByTypeService
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
        @RequestBody modifyNoticeRequest: ModifyNoticeRequest
    ) =
        modifyNoticeService.execute(id, modifyNoticeRequest)

    @PostMapping("/image")
    fun uploadImage(
        @RequestPart(name = "photo") image: MultipartFile
    ) =
        uploadNoticeImageService.execute(image)

    @GetMapping("/title")
    fun queryTitle() =
        queryNoticeTitleService.execute()

    @GetMapping
    fun getNoticeListByType(
        @RequestParam("type") type: NoticeType
    ): QueryNoticeResponse =
        queryNoticeListByTypeService.execute(type)

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    fun deleteNotice(
        @PathVariable(name = "notice-id")id: UUID
    ) =
        deleteNoticeService.execute(id)
}
