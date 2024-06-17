package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.attachFile.domain.repository.AttachFileRepository
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.AttachFileNotFoundException
import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateNoticeService(
    private val noticeRepository: NoticeRepository,
    private val attachFileRepository: AttachFileRepository,
    private val userUtils: UserUtils
) {

    @Transactional
    fun execute(
        request: CreateNoticeRequest
    ) {
        val admin = userUtils.getCurrentUserId()
        val attachFile = request.attachFile?.map {
            attachFileRepository.findByAttachFile(it) ?: throw AttachFileNotFoundException
        }

        noticeRepository.save(
            Notice(
                title = request.title,
                content = request.content,
                type = request.type,
                isPinned = request.isPinned,
                adminId = admin,
                fileName = request.fileName,
                attachFile = attachFile
            )
        )
    }
}
