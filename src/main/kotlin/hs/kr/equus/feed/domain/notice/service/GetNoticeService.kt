package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.equus.feed.domain.notice.presentation.dto.response.GetNoticeResponse
import hs.kr.equus.feed.infrastructure.s3.service.notice.NoticeS3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional(readOnly = true)
@Service
class GetNoticeService(
    private val noticeRepository: NoticeRepository,
    private val noticeS3Service: NoticeS3Service
) {
    fun execute(noticeId: UUID): GetNoticeResponse{
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        val imageURL = notice.fileName?.let { noticeS3Service.generateObjectUrl(it) }

        return notice.run {
            GetNoticeResponse(
                title = title,
                content = content,
                createdAt = createdAt,
                type = type,
                imageURL = imageURL
            )
        }
    }
}
