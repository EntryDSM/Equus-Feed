package hs.kr.equus.feed.domain.screen.domain.repository

import hs.kr.equus.feed.domain.screen.domain.Screen
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ScreenRepository : JpaRepository<Screen, UUID>
