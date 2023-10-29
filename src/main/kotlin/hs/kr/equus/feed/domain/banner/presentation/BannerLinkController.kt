package hs.kr.equus.feed.domain.banner.presentation

import hs.kr.equus.feed.domain.banner.service.CreateBannerLinkService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/banner")
@RestController
class BannerLinkController(
    private val createBannerLinkService: CreateBannerLinkService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createBannerLink(@RequestPart(name = "photo") file: MultipartFile): String =
        createBannerLinkService.execute(file)
}
