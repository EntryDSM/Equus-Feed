package hs.kr.equus.feed.global.utils.user

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserUtils {
    fun getCurrentUserId(): String = SecurityContextHolder.getContext().authentication.name
}
