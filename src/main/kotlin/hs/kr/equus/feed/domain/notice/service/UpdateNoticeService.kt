package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.attachFile.domain.repository.AttachFileRepository
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.AttachFileNotFoundException
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.equus.feed.domain.notice.presentation.dto.request.UpdateNoticeRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateNoticeService(
    private val noticeRepository: NoticeRepository,
    private val userUtils: UserUtils,
    private val fileUtil: FileUtil,
    private val attachFileRepository: AttachFileRepository
) {
    @Transactional
    fun execute(id: UUID, request: UpdateNoticeRequest): ResponseEntity<String> {
        val adminId = userUtils.getCurrentUser().id
        val notice = noticeRepository.findByIdOrNull(id) ?: throw NoticeNotFoundException
        val fileName = request.fileName
        val attachFile = request.attachFileName?.map {
            attachFileRepository.findByOriginalAttachFileName(it) ?: throw AttachFileNotFoundException
        }

        request.run {
            notice.modifyNotice(
                title = title,
                content = content,
                isPinned = isPinned,
                type = type,
                fileName = fileName,
                adminId = adminId,
                attachFile = attachFile
            )
        }

        fileName?.let { return ResponseEntity.ok(fileUtil.generateObjectUrl(it, PathList.NOTICE)) }
            ?: return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
