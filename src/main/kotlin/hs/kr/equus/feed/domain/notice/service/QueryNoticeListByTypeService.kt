package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import hs.kr.equus.feed.domain.notice.presentation.dto.response.NoticeDto
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryNoticeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryNoticeListByTypeService(
    private val noticeRepository: NoticeRepository
) {

    @Transactional(readOnly = true)
    fun execute(type: NoticeType): QueryNoticeResponse {
        val notices = getNoticeList(type).map { it ->
            NoticeDto(
                id = it.id!!,
                title = it.title,
                isPinned = it.isPinned,
                type = it.type,
                createAt = it.createdAt
            )
        }
        return QueryNoticeResponse(notices)
    }

    private fun getNoticeList(type: NoticeType?): List<Notice> {
        return type?.let { noticeRepository.findAllByType(it) } ?: noticeRepository.findAll()
    }
}
