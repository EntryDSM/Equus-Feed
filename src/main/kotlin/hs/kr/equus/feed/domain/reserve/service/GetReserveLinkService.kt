package hs.kr.equus.feed.domain.reserve.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GetReserveLinkService(
    @Value("\${reserve.link}")
    private val reserveLink: String
) {
    fun execute() =
        reserveLink
}
