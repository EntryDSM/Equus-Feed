package hs.kr.equus.feed.domain.question.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_question")
class Question(
    id: UUID?,

    @Column(name = "title", columnDefinition = "varchar(100)", nullable = false)
    val title: String,

    @Column(name = "content", columnDefinition = "varchar(1000)", nullable = false)
    val content: String,

    @Column(name = "is_public", columnDefinition = "BIT(1) default 0", nullable = false)
    val isPublic: Boolean,

    @Column(name = "is_replied", columnDefinition = "BIT(1) default 0", nullable = false)
    val isReplied: Boolean,

    @Column(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val userId: UUID
) : BaseEntity(id)
