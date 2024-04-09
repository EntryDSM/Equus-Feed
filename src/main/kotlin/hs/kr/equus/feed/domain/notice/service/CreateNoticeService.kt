package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryImageUrlResponse
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateNoticeService(
    private val s3Service: S3Service,
    private val noticeRepository: NoticeRepository,
    private val userUtils: UserUtils
) {
    companion object {
        const val PATH = "notice"
    }

    @Transactional
    fun execute(
        file: List<MultipartFile>?,
        request: CreateNoticeRequest
    ): QueryImageUrlResponse {
        val user = userUtils.getCurrentUserId()

        val files = file?.let { s3Service.upload(it, PATH) }

        val notice = Notice(
            adminId = user,
            title = request.title,
            content = request.content,
            type = request.type,
            isPinned = request.isPinned,
            fileName = files.toString()
        )

        noticeRepository.save(notice)
        return QueryImageUrlResponse(
            image = files!!
        )
    }
}
