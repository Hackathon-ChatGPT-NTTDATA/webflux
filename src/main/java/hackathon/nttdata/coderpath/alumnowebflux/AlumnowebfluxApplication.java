package hackathon.nttdata.coderpath.alumnowebflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Flux;
import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
public class AlumnowebfluxApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(AlumnowebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlumnowebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/* Flux<String> nombres = Flux.just("Joffre", "tangiro", "Mila", "Diego", "Joffre", "tangiro", "Mila", "Diego")
		*  .doOnNext(elemento -> System.out.println(elemento));
	   		
		*nombres.subscribe(log::info);
	
		* Flux<String> nombres = Flux.just("Joffre" , "tangiro" , "Mila" , "Diego" , "Fox" , "Mario" , "Fuigi" , "Juan")
		* .doOnNext(err -> {
		*	if(err.isEmpty()) {
		*	throw new RuntimeException("nombres no pueden ser vacios");
		*				}
		*	{System.out.println(err);}});
	
		* nombres.subscribe(err -> log.info(err),
	    * 	error -> log.error(error.getMessage()),
	    * new Runnable() {
					
	    *@Override
	    *public void run() {
	    *log.info("¡se a modificado la expersiòn con exito!");						
	    *}}
	    );
	    */


	/*	Flux<Alumno> nombres = Flux.just("Joffre", "tangiro", "Mila", "Diego", "Joffre", "tangiro", "Mila", "Diego")
				.map(nombre -> new Alumno (nombre.toUpperCase(), null, null, null, null))
				.doOnNext(alumno -> {
					if( alumno == null) {
					
				throw new RuntimeException("Nombre no pueden ser vacios");
				}
		         System.out.println(alumno.getNombre());
	         })
			.map(alumno -> {				
				String nombre = alumno.getNombre().toLowerCase();
				alumno.setNombre(nombre);
				return alumno;
			});			
			nombres.subscribe(e -> log.info(e.toString()),
			error -> log.error(error.getMessage()),
			  new Runnable() {
				
				@Override
				public void run() {
					log.info("has finalizado la ejecuciuon del observable con exito!");
				}
			});
	*/		
		
		
		Flux<Alumno> nombres = Flux.just("Joffre As" , "tangiro Yamaka" , "Yagami Lie" , "Mila Happy" , "Diego" , "Felix" , "Tangiro Yamaka" , "Pedro Pucho" , "Jose lan")
				.map(nombre -> new Alumno (nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase(), null, null, null))
				.filter(Alumno -> Alumno.getNombre().toLowerCase().equals("tangiro"))
				.doOnNext(alumno -> {
					if( alumno == null) {
					
				throw new RuntimeException("Nombre no pueden ser vacios");
				}
		         System.out.println(alumno.getNombre().concat(" ").concat(alumno.getApellido()));
	         })
			.map(alumno -> {				
				String nombre = alumno.getNombre().toLowerCase();
				alumno.setNombre(nombre);
				return alumno;
			});			
			nombres.subscribe(e -> log.info(e.toString()),
			error -> log.error(error.getMessage()),
			  new Runnable() {
				
				@Override
				public void run() {
					log.info("has finalizado la ejecuciuon del observable con exito!");
				}
			});
		
		
		

}

}
