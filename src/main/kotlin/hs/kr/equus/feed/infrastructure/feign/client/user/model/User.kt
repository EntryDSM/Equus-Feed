package hs.kr.equus.feed.infrastructure.feign.client.user.model

import hs.kr.equus.feed.global.security.jwt.UserRole
import java.util.UUID

data class User(
    val id: UUID,
    val phoneNumber: String,
    val name: String,
    val isParent: Boolean,
    val receiptCode: UUID?,
    val role: UserRole
)
