package hs.kr.equus.feed.domain.screen.presentation

import hs.kr.equus.feed.domain.screen.presentation.dto.response.QueryScreenResponse
import hs.kr.equus.feed.domain.screen.presentation.dto.response.ScreenResponse
import hs.kr.equus.feed.domain.screen.service.CreateScreenService
import hs.kr.equus.feed.domain.screen.service.QueryScreenService
import hs.kr.equus.feed.domain.screen.service.UpdateScreenService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/screen")
class ScreenController(
    private val createScreenService: CreateScreenService,
    private val updateScreenService: UpdateScreenService,
    private val queryScreenService: QueryScreenService
) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createScreen(
        @RequestPart(name = "image") image: MultipartFile
    ): ScreenResponse =
        createScreenService.execute(image)

    @PatchMapping("/{screen-id}")
    fun updateScreen(
        @PathVariable(name = "screen-id") id: UUID,
        @RequestPart(name = "image") image: MultipartFile
    ): ScreenResponse =
        updateScreenService.execute(id, image)

    @GetMapping
    fun queryScreen(): List<QueryScreenResponse> =
        queryScreenService.execute()
}
