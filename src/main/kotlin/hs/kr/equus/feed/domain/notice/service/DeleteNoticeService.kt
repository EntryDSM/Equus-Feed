package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.equus.feed.infrastructure.s3.service.S3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteNoticeService(
    private val noticeRepository: NoticeRepository,
    private val s3Service: S3Service
) {
    @Transactional
    fun execute(noticeId: UUID) {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        noticeRepository.deleteById(notice.id!!)
    }
}
