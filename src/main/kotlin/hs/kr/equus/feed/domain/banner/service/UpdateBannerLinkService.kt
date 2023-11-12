package hs.kr.equus.feed.domain.banner.service

import hs.kr.equus.feed.domain.banner.domain.BannerLink
import hs.kr.equus.feed.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.feed.domain.banner.exception.BannerLinkNotFoundException
import hs.kr.equus.feed.global.utils.s3.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class UpdateBannerLinkService (
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
){
    @Transactional
    fun execute(file : MultipartFile, id : UUID): String {
        val bannerLink : BannerLink = bannerLinkRepository.findById(id)
            .orElseThrow {BannerLinkNotFoundException}

        val fileName = s3Utils.upload(file, "banner/")

        bannerLink.updateBanner(link = fileName)

        return s3Utils.generateObjectUrl(fileName)
    }
}
