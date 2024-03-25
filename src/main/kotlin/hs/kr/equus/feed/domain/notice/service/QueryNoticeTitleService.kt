package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryNoticeTitleResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryNoticeTitleService(
    private val noticeRepository: NoticeRepository
) {

    @Transactional(readOnly = true)
    fun execute(): List<QueryNoticeTitleResponse> =
        noticeRepository.findAll()
            .map {
                    it ->
                QueryNoticeTitleResponse(
                    id = it.id!!,
                    title = it.title,
                    it.createdAt
                )
            }
}
