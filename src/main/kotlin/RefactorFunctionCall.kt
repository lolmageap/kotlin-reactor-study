import mu.KotlinLogging
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

fun plusOne(mono: Int): Mono<Int> = Mono.fromCallable { mono + 1 }
fun plusTwo(mono: Int): Mono<Int> = Mono.fromCallable { mono + 2 }

fun main() {
    getRequest()
        .doOnNext { logger.debug { "request == $it" } }
        .flatMap { plusOne(it) }
        .doOnNext { logger.debug { "plusOne == $it" } }
        .flatMap { plusTwo(it) }
        .doOnNext { logger.debug { "plusTwo == $it" } }
        .subscribe()
}