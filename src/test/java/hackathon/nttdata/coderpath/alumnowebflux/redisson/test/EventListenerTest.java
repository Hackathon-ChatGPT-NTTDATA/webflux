package hackathon.nttdata.coderpath.alumnowebflux.redisson.test;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.redisson.api.DeletedObjectListener;
import org.redisson.api.ExpiredObjectListener;
import org.redisson.api.RBucketReactive;
import org.redisson.client.codec.StringCodec;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class EventListenerTest extends BaseTest {

	//En redis-cli :  config set notify-keyspace-events AKE

	
	@Test
	public void expiredEventTest() {

		RBucketReactive<String> bucket = this.client.getBucket("user:1:name", StringCodec.INSTANCE);
		Mono<Void> set = bucket.set("joffre", 10, TimeUnit.SECONDS);
		Mono<Void> get = bucket.get().doOnNext(System.out::println).then();

		Mono<Void> event = bucket.addListener(new ExpiredObjectListener() {

			@Override
			public void onExpired(String s) {
				// TODO Auto-generated method stub
				System.out.println("Expired : " + s);
			}
		}).then();

		StepVerifier.create(set.concatWith(get).concatWith(event)).verifyComplete();

		// extend
		sleep(11000);

	}

	@Test
	public void deletedEventTest() {

		RBucketReactive<String> bucket = this.client.getBucket("user:1:name", StringCodec.INSTANCE);
		Mono<Void> set = bucket.set("joffre");
		Mono<Void> get = bucket.get().doOnNext(System.out::println).then();

		Mono<Void> event = bucket.addListener(new DeletedObjectListener() {

			@Override
			public void onDeleted(String s) {
				System.out.println("Deleted : " + s);

			}
		}).then();

		StepVerifier.create(set.concatWith(get).concatWith(event)).verifyComplete();

		// extend
		sleep(60000);

	}

}
