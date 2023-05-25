package hackathon.nttdata.coderpath.alumnowebflux.controller;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;
import java.util.Date;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AlumnoController {

	private final AlumnoService service;



	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		return ResponseEntity.ok(service.balanceadorTest());
	}

	@GetMapping("/all")
	public Flux<Alumno> searchAll() {
		Flux<Alumno> per = service.findAlls();
		log.info("PERSONAL ASSET registered: " + per);
		return per;
	}

	@GetMapping("/id/{id}")
	public Mono<Alumno> searchById(@PathVariable String id) {
		log.info("Personal Asset id: " + service.findById(id) + " con codigo: " + id);
		return service.findById(id);
	}

	@PostMapping("/create-student")
	public Mono<Alumno> createPersonalAsset(@Valid @RequestBody Alumno personalAsset) {
		log.info("Student hackathon NTTTDATA create: " + service.saves(personalAsset));
		Mono.just(personalAsset).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(personalAsset).onErrorResume(e -> Mono.just(personalAsset))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<Alumno> newPersonalAsset = service.saves(personalAsset);

		return newPersonalAsset;
	}

	@PutMapping("/update-student/{id}")
	public ResponseEntity<Mono<?>> updatePersonalAsset(@PathVariable String id,
			@Valid @RequestBody Alumno personalAsset) {
		Mono.just(personalAsset).doOnNext(t -> {
			personalAsset.setId(id);
			t.setCreateAt(new Date());

		}).onErrorReturn(personalAsset).onErrorResume(e -> Mono.just(personalAsset))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<Alumno> pAsset = service.saves(personalAsset);

		if (pAsset != null) {
			return new ResponseEntity<>(pAsset, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new Alumno()), HttpStatus.I_AM_A_TEAPOT);
	}

	@DeleteMapping("/delete-personal-asset/{id}")
	public ResponseEntity<Mono<Void>> deletePersonalAsset(@PathVariable String id) {
		Alumno personalAsset = new Alumno();
		personalAsset.setId(id);
		Mono<Alumno> newPersonalAsset = service.findById(id);
		newPersonalAsset.subscribe();
		Mono<Void> test = service.delete(personalAsset);
		test.subscribe();
		return ResponseEntity.noContent().build();
	}

}
