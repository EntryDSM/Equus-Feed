package hs.kr.equus.feed.global.utils.admin

import hs.kr.equus.feed.global.exception.InvalidTokenException
import hs.kr.equus.feed.infrastructure.feign.client.user.UserFeignClient
import hs.kr.equus.feed.infrastructure.feign.client.user.model.Admin
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AdminUtils(
    private val userFeignClient: UserFeignClient
) {
    fun getCurrentAdminId(): UUID {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException
        }
    }
    fun getCurrentAdmin(): Admin = userFeignClient.getAdminByUUID(getCurrentAdminId())
}
