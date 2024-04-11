package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteNoticeService(
    private val noticeRepository: NoticeRepository
) {
    @Transactional
    fun execute(noticeId: UUID) {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        noticeRepository.deleteById(notice.id!!)
    }
}
