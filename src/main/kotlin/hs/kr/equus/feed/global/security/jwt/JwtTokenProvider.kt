package hs.kr.equus.feed.global.security.jwt

import hs.kr.equus.feed.global.exception.ExpiredTokenException
import hs.kr.equus.feed.global.exception.InvalidTokenException
import hs.kr.equus.feed.global.security.auth.AuthDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {
    companion object {
        private const val ACCESS_KEY = "access_token"
        private const val REFRESH_KEY = "refresh_token"
    }

    fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(jwtProperties.header)?.also {
            if (it.startsWith(jwtProperties.prefix)) {
                return it.substring(jwtProperties.prefix.length)
            }
        }

    fun authentication(token: String): Authentication? {
        val body: Claims = getJws(token).body
        if (!isNotRefreshToken(token)) throw InvalidTokenException
        val userDetails: UserDetails = getDetails(body)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getJws(token: String): Jws<Claims> {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException
        } catch (e: Exception) {
            throw InvalidTokenException
        }
    }

    fun isNotRefreshToken(token: String?): Boolean {
        return REFRESH_KEY != getJws(token!!).header["typ"].toString()
    }

    private fun getDetails(body: Claims): UserDetails {
        return if (Role.USER.toString() == body["role"].toString()) {
            AuthDetails(body.subject)
        } else {
            // 어드민 구현 후 추가 예정
            AuthDetails(body.subject)
        }
    }
}

enum class Role {
    ROOT,
    CONFIRM_APPLICATION,
    USER
}
