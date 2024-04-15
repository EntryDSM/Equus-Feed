package hs.kr.equus.feed.domain.screen.presentation

import hs.kr.equus.feed.domain.screen.presentation.dto.response.ScreenResponse
import hs.kr.equus.feed.domain.screen.service.CreateScreenService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/screen")
class ScreenController(
    private val createScreenService: CreateScreenService
) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createScreen(
        @RequestPart(name = "image") image: MultipartFile
    ): ScreenResponse =
        createScreenService.execute(image)
}
