package hs.kr.equus.feed.domain.notice.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object NoticeNotFoundException : EquusException(
    ErrorCode.NOTICE_NOT_FOUND
)
