package hs.kr.equus.feed.domain.screen.domain

import hs.kr.equus.feed.domain.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Entity

@Entity(name = "tbl_screen")
class Screen (
    id: UUID?,

    val image: String,

    val adminId: UUID
): BaseUUIDEntity(id)
