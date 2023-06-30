package hackathon.nttdata.coderpath.alumnowebflux.services;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient.Cursos;

import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

	Mono<Alumno> findById(String id);

	Flux<Alumno> findAlls();

	Mono<Alumno> saves(Alumno document);

	Mono<Void> delete(Alumno document);

	Map<String, Object> balanceadorTest();
	
	Map<String, Object> rutaWebClientTest();

	/*
	 * seccion WEBCLIENT
	 */

	Flux<Cursos> findAll();

	Mono<Cursos> findCursosById(String id);

	Mono<Cursos> save(Cursos document);

	Mono<Cursos> update(Cursos document, String id);

	Mono<Void> delete(String id);

}
