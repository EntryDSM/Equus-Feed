package hs.kr.equus.feed.infrastructure.kafka.dto

import java.util.UUID

data class DeleteReplyEventRequest(
    val questionId: UUID
)
