package hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation;

import reactor.core.publisher.Mono;
	
interface BaseValidator<T> {
	
	Mono<T> validate (T t);

}
