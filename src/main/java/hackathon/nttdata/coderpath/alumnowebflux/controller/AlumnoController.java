package hackathon.nttdata.coderpath.alumnowebflux.controller;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;
import hackathon.nttdata.coderpath.alumnowebflux.util.Utileria;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

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

	/*
	 * @PutMapping("/editar-con-foto/{id}") public ResponseEntity<?>
	 * editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable
	 * String id,
	 * 
	 * @RequestParam MultipartFile archivo) throws IOException {
	 * 
	 * if (result.hasErrors()) { return this.validar(result); }
	 * 
	 * Mono<Alumno> o = service.findById(id); if (!o.isPresent()) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * Alumno alumnoDb = o.get(); alumnoDb.setNombre(alumno.getNombre());
	 * alumnoDb.setApellido(alumno.getApellido());
	 * alumnoDb.setEmail(alumno.getEmail()); if (!archivo.isEmpty()) {
	 * alumnoDb.setFoto(archivo.getBytes()); }
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.CREATED).body(service.saves(alumnoDb));
	 * 
	 * }
	 */

//	@PostMapping("/crear-con-foto")
	/*
	 * public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult
	 * result,
	 * 
	 * @RequestParam MultipartFile archivo, HttpServletRequest request) throws
	 * IOException { // TODO Auto-generated method stub
	 * 
	 * if (!archivo.isEmpty()) { alumno.setFoto(archivo.getBytes()); }
	 * 
	 * return super.crear(alumno, result); }
	 * 
	 * @PostMapping("/crear-con-fotoruta") public ResponseEntity<?>
	 * crearConFotoRuta(@Valid Alumno alumno, BindingResult result,
	 * 
	 * @RequestParam MultipartFile archivo, HttpServletRequest request) throws
	 * IOException {
	 * 
	 * int totalusuario = service.lastcode() + 1;
	 * 
	 * if (!archivo.isEmpty()) { // usuario.setFoto(archivo.getBytes());
	 * 
	 * String rutax = "/resources/images/usuarios/" + totalusuario;
	 * System.out.println("rutax: " + rutax); String nombreImagen =
	 * Utileria.guardarImagenPlus(archivo, request, rutax);
	 * 
	 * alumno.setRutafoto(nombreImagen);
	 * 
	 * }
	 * 
	 * return super.crear(alumno, result); }
	 * 
	 * 
	 * @PutMapping("/editar-con-fotoruta/{id}") public ResponseEntity<?>
	 * editarConFotoRuta(@Valid Alumno alumno, BindingResult result, @PathVariable
	 * String id,
	 * 
	 * @RequestParam MultipartFile archivo, HttpServletRequest request) throws
	 * IOException {
	 * 
	 * if (result.hasErrors()) { // return this.validar(result); }
	 * 
	 * Mono<Alumno> o = service.findById(id);
	 * 
	 * if (!o.isPresent()) { return ResponseEntity.notFound().build(); }
	 * 
	 * Alumno alumnoDb = o.get();
	 * 
	 * alumnoDb.setNombre(alumno.getNombre());
	 * alumnoDb.setApellido(alumno.getApellido());
	 * alumnoDb.setEmail(alumno.getEmail()); if (!archivo.isEmpty()) {
	 * alumno.setFoto(archivo.getBytes()); String rutax =
	 * "/resources/images/usuarios/" + alumno.getId(); System.out.println("rutax: "
	 * + rutax); String nombreImagen = Utileria.guardarImagenPlus(archivo, request,
	 * rutax); alumno.setRutafoto(nombreImagen);
	 * 
	 * } return
	 * ResponseEntity.status(HttpStatus.CREATED).body(service.saves(alumnoDb)); }
	 * 
	 * 
	 * @GetMapping("/uploads/img/{id}") public ResponseEntity<?>
	 * verFoto(@PathVariable Long id) {
	 * 
	 * Optional<Alumno> o = service.findById(id);
	 * 
	 * if (!o.isPresent() || o.get().getFoto() == null) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * Resource imagen = new ByteArrayResource(o.get().getFoto());
	 * 
	 * return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	 * 
	 * }
	 * 
	 */

}
