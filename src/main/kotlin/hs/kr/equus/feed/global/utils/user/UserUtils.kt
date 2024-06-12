package hs.kr.equus.feed.global.utils.user

import hs.kr.equus.feed.global.exception.InvalidTokenException
import hs.kr.equus.feed.infrastructure.feign.client.user.UserFeignClient
import hs.kr.equus.feed.infrastructure.feign.client.user.model.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserUtils(
    private val userFeignClient: UserFeignClient
) {
    fun getCurrentUserId(): UUID {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException
        }
    }
    fun getCurrentUser(): User = userFeignClient.getUserByUUID(getCurrentUserId())
}
