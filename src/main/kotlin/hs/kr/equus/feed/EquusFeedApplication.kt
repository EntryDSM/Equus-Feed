package hs.kr.equus.feed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class EquusFeedApplication

fun main(args: Array<String>) {
    runApplication<EquusFeedApplication>(*args)
}
