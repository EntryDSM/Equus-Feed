package hs.kr.equus.feed.infrastructure.kafka.dto

import java.util.UUID

data class CreateReplyEventRequest(
    val questionId: UUID
)
