package hs.kr.equus.feed.domain.reply.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object AccessDeniedReplyException : EquusException(
    ErrorCode.ACCESS_DENIED_REPLY
)
