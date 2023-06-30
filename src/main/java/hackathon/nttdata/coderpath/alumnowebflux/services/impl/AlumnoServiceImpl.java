package hackathon.nttdata.coderpath.alumnowebflux.services.impl;

import com.google.common.collect.Maps;
import hackathon.nttdata.coderpath.alumnowebflux.config.ApplicationConfiguration;
import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient.Cursos;
import hackathon.nttdata.coderpath.alumnowebflux.repository.AlumnoRepository;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.*;
import org.springframework.stereotype.Service;



import static org.springframework.web.reactive.function.BodyInserters.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

	private final AlumnoRepository alumnoRepository;

	private final ApplicationConfiguration configuration;

	@Autowired
	private WebClient client;

	@Override
	public Mono<Alumno> findById(String id) {
		// TODO Auto-generated method stub
		return alumnoRepository.findById(id);
	}

	@Override
	public Flux<Alumno> findAlls() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}

	@Override
	public Mono<Alumno> saves(Alumno document) {
		// TODO Auto-generated method stub
		return alumnoRepository.save(document);
	}

	@Override
	public Mono<Void> delete(Alumno document) {
		// TODO Auto-generated method stub
		return alumnoRepository.delete(document);
	}

	@Override
	public Map<String, Object> balanceadorTest() {
		Map<String, Object> response = Maps.newHashMap();
		response.put("balanceador", configuration.getBalanceadorTest());
		response.put("personal_asset", findAlls());
		return response;
	}

	// SECCION WEBCLIENT

	@Override
	public Flux<Cursos> findAll() {
		// TODO Auto-generated method stub
		System.out.println("ruta de cursos: " + client.toString());
		return client
				.get()
				.uri("/all")
				.accept(APPLICATION_JSON_UTF8)
				.exchange()
				.flatMapMany(response -> response.bodyToFlux(Cursos.class));
	}

	@Override
	public Mono<Cursos> findCursosById(String id) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id", id);

		return client
				.get()
				.uri("/{id}", params)
				.accept(APPLICATION_JSON_UTF8)
				.retrieve()
				.bodyToMono(Cursos.class);
				//.exchange()
				//.flatMap(response -> response.bodyToMono(Cursos.class));
				
		
		
	}

	@Override
	public Mono<Cursos> save(Cursos document) {
		// TODO Auto-generated method stub
		return client
				.post()
				.uri("/create-cursos")
				.accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8)
				.body(fromObject(document))
				//.syncBody(document)
				.retrieve()
				.bodyToMono(Cursos.class);
	}

	@Override
	public Mono<Cursos> update(Cursos document, String id) {
		// TODO Auto-generated method stub
		return client
				.put()
				.uri("/update-cursos/{id}", Collections.singletonMap("id", id))
				.accept(APPLICATION_JSON_UTF8)
				.contentType(APPLICATION_JSON_UTF8)
				//.body(fromObject(document))
				.syncBody(document)
				.retrieve()
				.bodyToMono(Cursos.class);
	}

	@Override
	public Mono<Void> delete(String id) {
		// TODO Auto-generated method stub
		return client
				.delete()
				.uri("/delete-cursos-asset/{id}", Collections.singletonMap("id", id))
				.exchange()
				.then();
	}

	@Override
	public Map<String, Object> rutaWebClientTest() {
		// TODO Auto-generated method stub
		Map<String, Object> response = Maps.newHashMap();
		response.put("balanceador", configuration.getUrlCursos());
		response.put("personal_asset", findAll());
		return response;
	}

}
