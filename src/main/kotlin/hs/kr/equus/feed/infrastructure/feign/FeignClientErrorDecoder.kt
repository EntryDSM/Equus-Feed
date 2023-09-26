package hs.kr.equus.feed.infrastructure.feign

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import hs.kr.equus.feed.infrastructure.feign.exception.FeignBadRequestException
import hs.kr.equus.feed.infrastructure.feign.exception.FeignForbiddenException
import hs.kr.equus.feed.infrastructure.feign.exception.FeignServerError
import hs.kr.equus.feed.infrastructure.feign.exception.FeignUnAuthorizedException

class FeignClientErrorDecoder: ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        if(response!!.status() >= 400) {
            when (response.status()) {
                400 -> throw FeignBadRequestException
                401 -> throw FeignUnAuthorizedException
                403 -> throw FeignForbiddenException
                else -> throw FeignServerError
            }
        }
        return FeignException.errorStatus(methodKey, response)
    }
}
