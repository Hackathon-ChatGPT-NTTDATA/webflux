package hackathon.nttdata.coderpath.alumnowebflux.redisson.test;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;
import org.redisson.client.codec.StringCodec;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class KeyValueTest extends BaseTest {
	/*
	 * @Test public void keyValueAccessTest() {
	 * 
	 * RBucketReactive<String> bucket = this.client.getBucket("alumno:1:name",
	 * StringCodec.INSTANCE); Mono<Void> set = bucket.set("carlo"); Mono<Void> get =
	 * bucket.get().doOnNext(System.out::println).then();
	 * 
	 * StepVerifier.create(set.concatWith(get)).verifyComplete(); }
	 * 
	 * @Test public void keyValueExpireTest() {
	 * 
	 * RBucketReactive<String> bucket = this.client.getBucket("alumno:1:name",
	 * StringCodec.INSTANCE); Mono<Void> set = bucket.set("carlo", 30,
	 * TimeUnit.SECONDS); Mono<Void> get =
	 * bucket.get().doOnNext(System.out::println).then();
	 * 
	 * StepVerifier.create(set.concatWith(get)).verifyComplete(); }
	 * 
	 * @Test public void keyValueExtendExpireTest() {
	 * 
	 * RBucketReactive<String> bucket = this.client.getBucket("joffre:1:name",
	 * StringCodec.INSTANCE); Mono<Void> set = bucket.set("joffre", 10,
	 * TimeUnit.SECONDS); Mono<Void> get =
	 * bucket.get().doOnNext(System.out::println).then();
	 * 
	 * StepVerifier.create(set.concatWith(get)).verifyComplete();
	 * 
	 * // extend sleep(5000); Mono <Boolean> mono = bucket.expire(30,
	 * TimeUnit.SECONDS); StepVerifier.create(mono) .expectNext(true)
	 * .verifyComplete();
	 * 
	 * //access expiration time
	 * 
	 * Mono<Void> ttl = bucket.remainTimeToLive() .doOnNext(System.out::println)
	 * .then(); StepVerifier.create(ttl) .verifyComplete();
	 * 
	 * 
	 * }
	 */}
