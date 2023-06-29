package hackathon.nttdata.coderpath.alumnowebflux.controller.handler;

import static org.springframework.http.MediaType.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


import hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient.Cursos;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;

import reactor.core.publisher.Mono;

@Component
public class CursoHandler {

	private AlumnoService service;

	public Mono<ServerResponse> listar(ServerRequest request) {

		return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8).body(service.findAll(), Cursos.class);
	}

	public Mono<ServerResponse> getOne(ServerRequest request) {

		String id = request.pathVariable("id");

		return service.findCursosById(id).flatMap(c -> ServerResponse.ok().contentType(APPLICATION_JSON_UTF8)
				.syncBody(c).switchIfEmpty(ServerResponse.notFound().build()));
	}
	
	
	
	

}
