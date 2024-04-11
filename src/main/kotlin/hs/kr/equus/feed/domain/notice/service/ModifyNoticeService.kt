package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.equus.feed.domain.notice.presentation.dto.request.ModifyNoticeRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.s3.service.S3ServiceImpl
import hs.kr.equus.feed.infrastructure.s3.service.notice.NoticeS3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ModifyNoticeService(
    private val noticeRepository: NoticeRepository,
    private val userUtils: UserUtils,
    private val noticeS3Service: NoticeS3Service
) {
    @Transactional
    fun execute(id: UUID, request: ModifyNoticeRequest): ResponseEntity<String> {
        val adminId = userUtils.getCurrentUser().id
        val notice = noticeRepository.findByIdOrNull(id) ?: throw NoticeNotFoundException
        val fileName = request.fileName

        request.run {
            notice.modifyNotice(
                title = title,
                content = content,
                isPinned = isPinned,
                type = type,
                fileName = fileName,
                adminId = adminId
            )
        }

        fileName?.let { return ResponseEntity.ok(noticeS3Service.generateObjectUrl(it)) }
            ?: return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
