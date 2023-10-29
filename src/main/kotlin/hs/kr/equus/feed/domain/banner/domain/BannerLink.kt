package hs.kr.equus.feed.domain.banner.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_banner_link")
class BannerLink(
    id: UUID? = null,

    @Column(name = "link", nullable = false)
    val link: String
) : BaseEntity(id)
