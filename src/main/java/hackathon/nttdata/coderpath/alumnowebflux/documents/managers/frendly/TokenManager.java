package hackathon.nttdata.coderpath.alumnowebflux.documents.managers.frendly;

import reactor.core.publisher.Mono;

public interface TokenManager {
	
    String issueToken (String userId);

    Mono<String> parse (String token);

}
