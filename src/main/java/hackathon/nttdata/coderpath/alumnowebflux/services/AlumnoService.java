package hackathon.nttdata.coderpath.alumnowebflux.services;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient.Cursos;

import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

	Mono<Alumno> findById(String id);

	Flux<Alumno> findAlumonos();

	Mono<Alumno> saveAlumno(Alumno document);

	Mono<Void> deleteAlumno(Alumno document);

	Map<String, Object> balanceadorTest();
	
	Map<String, Object> rutaWebClientTest();

	/*
	 * seccion WEBCLIENT
	 */

	Flux<Cursos> findCursos();

	Mono<Cursos> findCursosById(String id);

	Mono<Cursos> saveCurso(Cursos document);

	Mono<Cursos> updateCurso(Cursos document, String id);

	Mono<Void> deleteCurso(String id);
	
	

	//con Alumno y Curso
	Mono<Alumno> savesAlumnoCurso(Alumno document, String cursoId);

}
