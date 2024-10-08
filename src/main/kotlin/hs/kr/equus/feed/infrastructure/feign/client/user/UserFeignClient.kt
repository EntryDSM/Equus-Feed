package hs.kr.equus.feed.infrastructure.feign.client.user

import hs.kr.equus.feed.infrastructure.feign.client.user.model.Admin
import hs.kr.equus.feed.infrastructure.feign.client.user.model.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "UserClient", url = "\${url.user}")
interface UserFeignClient {
    @GetMapping("/user/{userId}")
    fun getUserByUUID(@PathVariable userId: UUID): User

    @GetMapping("/admin/{adminId}")
    fun getAdminByUUID(@PathVariable adminId: UUID): Admin
}
