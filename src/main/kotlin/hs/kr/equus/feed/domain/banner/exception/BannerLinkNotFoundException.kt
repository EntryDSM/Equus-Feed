package hs.kr.equus.feed.domain.banner.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object BannerLinkNotFoundException : EquusException (
    ErrorCode.BANNERLINK_NOT_FOUND
)
