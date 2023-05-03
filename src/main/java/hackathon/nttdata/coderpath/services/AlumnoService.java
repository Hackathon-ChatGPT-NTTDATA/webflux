package hackathon.nttdata.coderpath.services;

import hackathon.nttdata.coderpath.documents.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

	public Mono<Alumno> findById(String id);

	public Flux<Alumno> findAlls();

	public Mono<Alumno> saves(Alumno document);

	public Mono<Void> delete(Alumno document);

}
