package hackathon.nttdata.coderpath.alumnowebflux.redisson.test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongReactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class NumberStoreTest extends BaseTest {

	@Test
	public void keyValueIncreaseTest() {
		
		
		RAtomicLongReactive atomicLong = this.client.getAtomicLong("user:1:visit");
	//	atomicLong.
		
		AtomicLong atomicLong1 = new AtomicLong();
		
	Mono<Void> mono =	Flux.range(1, 30)
		.delayElements(Duration.ofSeconds(1))
		.flatMap(i -> atomicLong.incrementAndGet())
		.then();
	StepVerifier.create(mono)
		.verifyComplete();
		
		
	}
	
}
