package hs.kr.equus.feed.domain.faq.presentation.dto.response

import hs.kr.equus.feed.domain.faq.domain.type.FaqType
import java.util.UUID

data class FaqTitleAndTypeResponse(
    val id: UUID,
    val type: FaqType,
    val title: String
)
