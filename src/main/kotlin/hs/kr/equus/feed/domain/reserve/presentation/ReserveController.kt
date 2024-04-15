package hs.kr.equus.feed.domain.reserve.presentation

import hs.kr.equus.feed.domain.reserve.service.GetReserveLinkService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reserve")
class ReserveController(
    private val getReserveLinkService: GetReserveLinkService
) {

    @GetMapping
    fun reserveLink(): String =
        getReserveLinkService.execute()
}
