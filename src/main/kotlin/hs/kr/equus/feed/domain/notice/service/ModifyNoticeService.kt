package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.equus.feed.domain.notice.presentation.dto.request.ModifyNoticeRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.s3.service.S3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ModifyNoticeService(
    private val noticeRepository: NoticeRepository,
    private val userUtils: UserUtils,
    private val s3Service: S3Service
) {
    companion object {
        const val PATH = "notice/"
    }

    @Transactional
    fun execute(id: UUID, request: ModifyNoticeRequest): String {
        val adminId = userUtils.getCurrentUser().id
        val notice = noticeRepository.findByIdOrNull(id) ?: throw NoticeNotFoundException

        request.run {
            notice.modifyNotice(
                title = title,
                content = content,
                isPinned = isPinned,
                type = type,
                fileName = request.fileName,
                adminId = adminId
            )
        }
        return s3Service.generateObjectUrl(request.fileName, PATH)
    }
}
