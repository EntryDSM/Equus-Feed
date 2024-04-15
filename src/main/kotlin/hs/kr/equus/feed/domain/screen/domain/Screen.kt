package hs.kr.equus.feed.domain.screen.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.UUID
import javax.persistence.Entity

@Entity(name = "tbl_screen")
class Screen(

    id: UUID? = null,

    val image: String,

    val adminId: UUID
) : BaseEntity(id)
