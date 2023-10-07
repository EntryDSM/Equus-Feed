package hs.kr.equus.feed.global.utils.user

import hs.kr.equus.feed.global.utils.user.dto.UserInfo
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserUtils {
    fun getCurrentUserId(): UUID = UUID.fromString(SecurityContextHolder.getContext().authentication.name)

    fun getCurrentUserRole(): String = SecurityContextHolder.getContext().authentication.authorities.toString()

    fun getCurrentUserIdAndRole(): UserInfo = UserInfo(
        userId = getCurrentUserId(),
        userRole = getCurrentUserRole()
    )
}
