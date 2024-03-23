package hs.kr.equus.feed.domain.faq.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object FaqNotFoundException : EquusException(
    ErrorCode.FAQ_NOT_FOUND
)
