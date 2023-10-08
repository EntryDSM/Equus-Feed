package hs.kr.equus.feed.global.utils.user.dto

import java.util.UUID

data class UserInfo(
    val userId: UUID,
    val userRole: String
)
