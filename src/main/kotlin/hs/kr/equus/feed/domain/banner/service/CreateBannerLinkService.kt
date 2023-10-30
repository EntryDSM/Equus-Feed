package hs.kr.equus.feed.domain.banner.service

import hs.kr.equus.feed.domain.banner.domain.BannerLink
import hs.kr.equus.feed.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.feed.global.utils.s3.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateBannerLinkService(
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
) {
    @Transactional
    fun execute(file: MultipartFile): String {
        bannerLinkRepository.save(BannerLink(fileName = file.name))
        return s3Utils.generateObjectUrl(file.name)
    }
}
