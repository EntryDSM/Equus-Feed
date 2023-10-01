package hs.kr.equus.feed.infrastructure.feign.client.user.dto.response

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val phoneNumber: String,
    val name: String,
    val isParent: Boolean,
    val receiptCode: UUID?
)
