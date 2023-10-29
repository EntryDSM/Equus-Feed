package hs.kr.equus.feed.domain.banner.domain.repository

import hs.kr.equus.feed.domain.banner.domain.BannerLink
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BannerLinkRepository : JpaRepository<BannerLink, UUID>
