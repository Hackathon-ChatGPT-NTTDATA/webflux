package hackathon.nttdata.coderpath.alumnowebflux.services.impl;

import com.google.common.collect.Maps;
import hackathon.nttdata.coderpath.alumnowebflux.config.ApplicationConfiguration;
import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.repository.AlumnoRepository;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {
	private final AlumnoRepository alumnoRepository;
	private final ApplicationConfiguration configuration;

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

}
