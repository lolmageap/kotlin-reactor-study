import mu.KotlinLogging
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

fun getRequest(): Mono<Int> = Mono.just(1)

fun plusOne(mono: Mono<Int>): Mono<Int> = mono.map { it + 1 }

fun plusTwo(mono: Mono<Int>): Mono<Int> = mono.map { it + 2 }

fun main() {
    val request = getRequest().doOnNext { logger.debug { "request == $it" } }
    val plusOne = plusOne(request).doOnNext { logger.debug { "plusOne == $it" } }
    val plusTwo = plusTwo(plusOne).doOnNext { logger.debug { "plusTwo == $it" } }
    plusTwo.subscribe()
}