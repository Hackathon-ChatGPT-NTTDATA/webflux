package hackathon.nttdata.coderpath.alumnowebflux.controller.handler;

import static org.springframework.http.MediaType.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import hackathon.nttdata.coderpath.alumnowebflux.controller.validation.ObjectValidator;
import hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux.Product;

import hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient.Cursos;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;
import hackathon.nttdata.coderpath.alumnowebflux.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class CursoHandler {
	

    private final ObjectValidator objectValidator;


	@Autowired
	private AlumnoService service;

	public Mono<ServerResponse> listar(ServerRequest request) {

		return ServerResponse
				.ok()
				.contentType(APPLICATION_JSON_UTF8)
				.body(service.findCursos(), Cursos.class);
	}

	public Mono<ServerResponse> getOne(ServerRequest request) {

		String id = request.pathVariable("id");

		return service.findCursosById(id).flatMap(c -> ServerResponse
				.ok()
				.contentType(APPLICATION_JSON_UTF8)
				.syncBody(c)
				.switchIfEmpty(ServerResponse.notFound()
				.build()));
	}
	
	  public Mono<ServerResponse> save(ServerRequest request) {
	        Mono<Cursos> dtoMono = request.bodyToMono(Cursos.class)
	        		.doOnNext(objectValidator::validate);
	        return dtoMono.flatMap(productDto -> ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.saveCurso(productDto), Cursos.class));
	    }

	    public Mono<ServerResponse> update(ServerRequest request) {
	        String id = request.pathVariable("id");
	        Mono<Cursos> dtoMono = request.bodyToMono(Cursos.class)
	        		.doOnNext(objectValidator::validate);
	        return dtoMono.flatMap(c -> ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.updateCurso(c, id), Cursos.class));
	    }

	    public Mono<ServerResponse> delete(ServerRequest request) {
	    	String id = request.pathVariable("id");
	        return ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.deleteCurso(id), Cursos.class);
	    }
	
	

}
