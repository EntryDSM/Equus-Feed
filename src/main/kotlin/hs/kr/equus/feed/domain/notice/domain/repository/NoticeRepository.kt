package hs.kr.equus.feed.domain.notice.domain.repository

import hs.kr.equus.feed.domain.notice.domain.Notice
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NoticeRepository : JpaRepository<Notice, UUID>
