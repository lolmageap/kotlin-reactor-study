import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

fun main() {
//    mono()
//    fluxStream()
//    fluxArray()
//    fluxRange()
//    fluxFlatMap()
    monoToFlux()
}


fun mono() {
    Mono.just(5)
        .map { it + 5 }
        .doOnNext {
            logger.debug { "로그 출력 : $it" }
        }.subscribe()
}

fun fluxStream() {
    Flux.just(1,3,5,7,9)
        .map { it + 1 }
        .log()
        .subscribe()
}

fun fluxArray() {
    Flux.just(arrayOf(1,2,3,4), arrayOf(7,8,9))
        .map { it + 1 }
        .log()
        .subscribe()
}

fun fluxRange() {
    /**
     * map == block 방식의 io 방식
     */
    Flux.range(1, 10)
        .map { it * it }
        .log()
        .subscribe()
}

fun fluxFlatMap() {
    /**
     * flatMap == non block 방식의 io 방식
     */
    Flux.range(1, 10)
        .flatMap { Mono.just(it * it) }
        .log()
        .subscribe()
}

fun monoToFlux() {
    /**
     * mono -> flux 로 변환
     */
    Mono.just(1)
        .map { it + 5 }
        .flux()
        .log()
        .subscribe()
}