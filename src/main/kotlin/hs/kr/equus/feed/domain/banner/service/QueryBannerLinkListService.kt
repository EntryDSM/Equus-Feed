package hs.kr.equus.feed.domain.banner.service

import hs.kr.equus.feed.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.feed.domain.banner.presentation.dto.response.BannerLinkListResponse
import hs.kr.equus.feed.domain.banner.presentation.dto.response.BannerLinkResponse
import hs.kr.equus.feed.global.utils.s3.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryBannerLinkListService(
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
) {
    @Transactional(readOnly = true)
    fun execute(): BannerLinkListResponse{
        val bannerLinks = bannerLinkRepository.findAll().map {
            BannerLinkResponse(s3Utils.generateObjectUrl(it.fileName))
        }
        return BannerLinkListResponse(bannerLinks)
    }
}
