package hackathon.nttdata.coderpath.alumnowebflux.services.impl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.repository.AlumnoRepository;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	AlumnoRepository repository;

	@Override
	public Mono<Alumno> findById(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Flux<Alumno> findAlls() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<Alumno> saves(Alumno document) {
		// TODO Auto-generated method stub
		return repository.save(document);
	}

	@Override
	public Mono<Void> delete(Alumno document) {
		// TODO Auto-generated method stub
		return repository.delete(document);
	}

}
