package hs.kr.equus.feed.infrastructure.feign

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import hs.kr.equus.feed.infrastructure.feign.exception.FeignServerError

class FeignClientErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        if (response!!.status() >= 400) {
            throw FeignServerError
        }
        return FeignException.errorStatus(methodKey, response)
    }
}
