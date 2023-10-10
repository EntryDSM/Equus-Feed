package hs.kr.equus.feed.domain.reply.domain.repository

import hs.kr.equus.feed.domain.reply.domain.Reply
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ReplyRepository : JpaRepository<Reply, UUID>
