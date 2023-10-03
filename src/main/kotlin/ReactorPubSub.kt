import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

private val logger = KotlinLogging.logger {}
private val scheduler = Schedulers.newSingle("worker")

fun main() {
    Flux.range(1, 12)
        .doOnNext { logger.debug { "param : $it" } }
        .filter { it % 2 == 0 }
        .doOnNext { logger.debug { "filtered 2 : $it" } }
        .filter { it % 3 == 0 }
        .delayElements(Duration.ofMillis(10), scheduler)
        .doOnNext { logger.debug { "filtered 3 : $it" } }
        .filter { it % 4 == 0 }
        .doOnNext { logger.debug { "filtered 4 : $it" } }
        .subscribeOn(scheduler)
        .subscribe()
}