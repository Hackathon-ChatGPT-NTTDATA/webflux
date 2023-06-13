package hackathon.nttdata.coderpath.alumnowebflux.services;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

	Mono<Alumno> findById(String id);

	Flux<Alumno> findAlls();

	Mono<Alumno> saves(Alumno document);

	Mono<Void> delete(Alumno document);

	Map<String, Object> balanceadorTest();

	
	
	
	
	
}
