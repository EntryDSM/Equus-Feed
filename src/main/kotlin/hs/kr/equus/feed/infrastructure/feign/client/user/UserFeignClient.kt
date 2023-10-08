package hs.kr.equus.feed.infrastructure.feign.client.user

import hs.kr.equus.feed.infrastructure.feign.client.user.model.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(name = "UserClient", url = "\${url.user}")
interface UserFeignClient {
    @GetMapping("/user")
    fun getUserByUUID(@RequestParam("userId") userId: UUID): User
}
