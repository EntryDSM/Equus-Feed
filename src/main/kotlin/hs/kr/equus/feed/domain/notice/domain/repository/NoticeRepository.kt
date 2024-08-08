package hs.kr.equus.feed.domain.notice.domain.repository

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NoticeRepository : JpaRepository<Notice, UUID> {
    fun findAllByType(type: NoticeType): List<Notice>
}
