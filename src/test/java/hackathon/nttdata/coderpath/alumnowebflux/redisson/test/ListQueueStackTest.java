package hackathon.nttdata.coderpath.alumnowebflux.redisson.test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.redisson.api.RDequeReactive;
import org.redisson.api.RListReactive;

import org.redisson.api.RQueueReactive;
import org.redisson.client.codec.LongCodec;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ListQueueStackTest extends BaseTest {
	/*
	 * @Test public void listTest() { // lrange number.input 0 -1
	 * RListReactive<Long> list = this.client.getList("number-input",
	 * LongCodec.INSTANCE);
	 * 
	 * List<Long> longlist = LongStream.rangeClosed(1,
	 * 10).boxed().collect(Collectors.toList());
	 * 
	 * 
	 * Mono<Void> listAdd = Flux.range(1, 10) .map(Long::valueOf)
	 * .flatMap(list::add).then();
	 * 
	 * 
	 * StepVerifier.create(list.addAll(longlist).then()).verifyComplete();
	 * 
	 * StepVerifier.create(list.size()).expectNext(10).verifyComplete(); }
	 * 
	 * @Test public void queueTest() { // lrange number.input 0 -1
	 * RQueueReactive<Long> queue = this.client.getQueue("number-input",
	 * LongCodec.INSTANCE);
	 * 
	 * Mono<Void> queuePoll
	 * =queue.poll().repeat(3).doOnNext(System.out::println).then();
	 * 
	 * 
	 * StepVerifier.create(queuePoll).verifyComplete();
	 * 
	 * StepVerifier.create(queue.size()).expectNext(6).verifyComplete();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test public void stackTest() { // lrange number.input 0 -1
	 * RQueueReactive<Long> deque = this.client.getDeque("number-input",
	 * LongCodec.INSTANCE);
	 * 
	 * Mono<Void> stackPoll = ((RDequeReactive<Long>) deque).pollLast() .repeat(3)
	 * .doOnNext(System.out::println).then();
	 * 
	 * 
	 * StepVerifier.create(stackPoll).verifyComplete();
	 * 
	 * StepVerifier.create(deque.size()).expectNext(2).verifyComplete();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
