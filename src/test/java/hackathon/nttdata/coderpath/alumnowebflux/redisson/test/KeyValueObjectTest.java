package hackathon.nttdata.coderpath.alumnowebflux.redisson.test;

import java.util.Arrays;



import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;

import org.redisson.codec.TypedJsonJacksonCodec;

import hackathon.nttdata.coderpath.alumnowebflux.redisson.test.dto.Student;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class KeyValueObjectTest extends BaseTest {
	
	/*
	 * @Test public void KeyValueObjectTestgetBucket() { Student student = new
	 * Student("marshal",23,"atlanta",Arrays.asList(1,2,3));
	 * //RBucketReactive<Student> bucket = this.client.getBucket("student:1",
	 * JsonJacksonCodec.INSTANCE); RBucketReactive<Student> bucket =
	 * this.client.getBucket("student:1", new TypedJsonJacksonCodec(Student.class));
	 * Mono<Void> set = bucket.set(student); Mono<Void> get = bucket.get()
	 * .doOnNext(System.out::println) .then();
	 * StepVerifier.create(set.concatWith(get)) .verifyComplete();
	 * 
	 * }
	 */
}
