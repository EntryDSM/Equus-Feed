package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import hs.kr.equus.feed.domain.notice.presentation.dto.response.NoticeResponse
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryListNoticeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryNoticeListByTypeService(
    private val noticeRepository: NoticeRepository
) {

    @Transactional(readOnly = true)
    fun execute(type: NoticeType?): QueryListNoticeResponse {
        val notices = getNoticeList(type).map { it ->
            NoticeResponse(
                id = it.id!!,
                title = it.title,
                isPinned = it.isPinned,
                type = it.type,
                createdAt = it.createdAt
            )
        }.sortedWith(compareByDescending<NoticeResponse> { it.isPinned }
            .thenByDescending { it.createdAt })

        return QueryListNoticeResponse(notices)
    }

    private fun getNoticeList(type: NoticeType?): List<Notice> {
        return type?.let { noticeRepository.findAllByType(it) } ?: noticeRepository.findAll()
    }
}
