package hackathon.nttdata.coderpath.alumnowebflux.controller;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.services.AlumnoService;
import hackathon.nttdata.coderpath.alumnowebflux.services.impl.KafkaProducer;

import java.util.Date;

import jakarta.validation.Valid;
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

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;

import org.springframework.http.server.ServerHttpRequest;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;







@RestController
@Slf4j
@RequiredArgsConstructor
//*@CrossOrigin({"*"})
//*@CrossOrigin({"http://localhost:4200"})
public class AlumnoController {

	private final AlumnoService service;

	private final KafkaProducer producer;

	final String urlServer = "http://localhost:8090/api/alumnowebflux";

	//// validadores de conexion webclient
	@GetMapping("/{param}")
	public Mono<ResponseEntity<Mono<String>>> testGet(@PathVariable String param) {
		final long dateStarted = System.currentTimeMillis();

		WebClient webClient = WebClient.create(urlServer + "/server/");
		Mono<ClientResponse> respuesta = webClient.get().uri("?queryParam={name}", param).exchange();
		Mono<ClientResponse> respuesta1 = webClient.get()
				.uri("?queryParam={name}", "SPEED".equals(param) ? "SPEED" : "STOP").exchange();

		Mono<ResponseEntity<Mono<String>>> f1 = Mono.zip(respuesta, respuesta1).map(t -> {
			if (!t.getT1().statusCode().is2xxSuccessful()) {
				return ResponseEntity.status(t.getT1().statusCode()).body(t.getT1().bodyToMono(String.class));
			}
			if (!t.getT2().statusCode().is2xxSuccessful()) {
				return ResponseEntity.status(t.getT2().statusCode()).body(t.getT2().bodyToMono(String.class));
			}
			return ResponseEntity.ok().body(Mono.just(
					"All OK. Seconds elapsed: " + (((double) (System.currentTimeMillis() - dateStarted) / 1000))));
		});
		return f1;
	}

	@PostMapping("")
	public Mono<String> testURLs(@RequestBody Map<String, String> body, @RequestParam(required = false) String url) {

		log.debug("Client: in testURLs");
		WebClient.Builder builder = WebClient.builder().baseUrl(urlServer).defaultHeader(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON_VALUE);
		if (body != null && body.size() > 0) {
			for (Map.Entry<String, String> map : body.entrySet()) {
				builder.defaultHeader(map.getKey(), map.getValue());
			}
		}
		WebClient webClient = builder.build();
		String urlFinal;
		if (url == null)
			urlFinal = "/server/post";
		else
			urlFinal = "/server/" + url;

		Mono<String> respuesta1 = webClient.post().uri(urlFinal).body(BodyInserters.fromObject(body)).exchange()
				.flatMap(x -> {
					if (!x.statusCode().is2xxSuccessful())
						return Mono.just("LLamada a " + urlServer + urlFinal + " Error 4xx: " + x.statusCode() + "\n");
					return x.bodyToMono(String.class);
				});
		return respuesta1;
	}

	@PostMapping("post")
	public ResponseEntity<String> postExample(@RequestBody Map<String, String> body, ServerHttpRequest request) {
		String s = "the server said: " + body + "\n";
		for (Map.Entry<String, List<String>> map : request.getHeaders().entrySet()) {
			s += "Headers: " + map.getKey() + ":" + map.getValue().get(0) + "\n";
		}
		return ResponseEntity.ok().body(s);
	}

	//// prueba webclient

	@PostMapping("/producer/{topic}")
	public ResponseEntity<Mono<?>> publishMessage(@PathVariable String topic, @Valid @RequestBody String message) {
		Mono.just(producer).doOnNext(t -> {

			t.publishMessage(topic, message);

		}).onErrorReturn(producer).onErrorResume(e -> Mono.just(producer))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<String> pAsset = Mono.just(message);

		if (pAsset != null) {
			return new ResponseEntity<>(pAsset, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new Alumno()), HttpStatus.I_AM_A_TEAPOT);
	}

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		return ResponseEntity.ok(service.balanceadorTest());
	}

	@GetMapping("/webclient-test")
	public ResponseEntity<?> rutaWebClientTest() {
		return ResponseEntity.ok(service.rutaWebClientTest());
	}

	@GetMapping("/all")
	public Flux<Alumno> searchAll() {
		Flux<Alumno> per = service.findAlumonos();
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
		log.info("Student hackathon NTTTDATA create: " + service.saveAlumno(personalAsset));
		Mono.just(personalAsset).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(personalAsset).onErrorResume(e -> Mono.just(personalAsset))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<Alumno> newPersonalAsset = service.saveAlumno(personalAsset);

		return newPersonalAsset;
	}

	@PostMapping("/create-student/curso/{id}")
	public Mono<Alumno> createAlumnoConCurso(@PathVariable String id, @Valid @RequestBody Alumno personalAsset) {

		log.info("Student hackathon NTTTDATA create: " + service.savesAlumnoCurso(personalAsset, id));
		Mono.just(personalAsset).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(personalAsset).onErrorResume(e -> Mono.just(personalAsset))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> log.info(x.toString()));

		Mono<Alumno> newPersonalAsset = service.savesAlumnoCurso(personalAsset, id);

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

		Mono<Alumno> pAsset = service.saveAlumno(personalAsset);

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
		Mono<Void> test = service.deleteAlumno(personalAsset);
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
