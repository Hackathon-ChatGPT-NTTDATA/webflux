package hackathon.nttdata.coderpath.alumnowebflux;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.google.common.eventbus.Subscribe;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import hackathon.nttdata.coderpath.alumnowebflux.documents.AlumnoComentarios;
import hackathon.nttdata.coderpath.alumnowebflux.documents.Comentarios;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;


@EnableEurekaClient
@SpringBootApplication
public class AlumnowebfluxApplication implements CommandLineRunner {
	


	private static final Logger log = LoggerFactory.getLogger(AlumnowebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlumnowebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------------Ejemplo 1------------------------------------");
		ejemplo1();
		System.out.println("-------------------Ejemplo 2------------------------------------");
		ejemplo1();
		System.out.println("-------------------Ejemplo 3------------------------------------");
		ejemplo3();
		System.out.println("-------------------Ejemplo 4------------------------------------");
		ejemplo4();
		System.out.println("-------------------Ejemplo 5------------------------------------");
		ejemplo5();
		System.out.println("-------------------Ejemplo 6------------------------------------");
		ejemplo6();
		System.out.println("-------------------ejemplo 7------------------------------------");
		ejemplo7();
		System.out.println("-------------------ejemplo 8------------------------------------");
		ejemplo8();
		System.out.println("-------------------ejemplo 9------------------------------------");
		ejemplo9();
		System.out.println("-------------------ejemplo 10------------------------------------");
		ejemplo10();
		System.out.println("-------------------ejemplo 11------------------------------------");
		ejemplo11();
		System.out.println("-------------------ejemplo 12------------------------------------");
		ejemplo12();
		}
		
		/* Flux<String> nombres = Flux.just("Joffre", "tangiro", "Mila", "Diego", "Joffre", "tangiro", "Mila", "Diego")
		*  .doOnNext(elemento -> System.out.println(elemento));
	   		
		nombres.subscribe(log::info);
		*/	
		public void ejemplo1() {
			
		 Flux<String> nombres = Flux.just("Joffre" , "tangiro" , "Mila" , "Diego" , "Fox" , "Mario" , "Fuigi" , "Juan")
		 .doOnNext(err -> {
			if(err.isEmpty()) {
			throw new RuntimeException("nombres no pueden ser vacios");
						}
			{System.out.println(err);}});
	
		 nombres.subscribe(err -> log.info(err),
	     	error -> log.error(error.getMessage()),
	     new Runnable() {
					
	    @Override
	    public void run() {
	    log.info("¡se a modificado la expersiòn con exito!");						
	    }}
	    );
		}
		
		public void ejemplo2() {
		Flux<Alumno> nombres = Flux.just("Joffre", "tangiro", "Mila", "Diego", "Joffre", "tangiro", "Mila", "Diego")
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
		}
	
	public void ejemplo3() {
		Flux<Alumno> nombres = Flux.just("Joffre As" , "Tangiro Yamaka" , "Yagami Lie" , "Mila Happy" , "Diego SANTOS" , "Felix Shell" , "Tangiro Yanaka" , "Pedro Pucho" , "Jose lan")
				.map(nombre -> new Alumno (nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase(), null, null, null))
				.filter(Alumno -> Alumno.getNombre().toLowerCase().equals("Tangiro"))
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

	public void ejemplo4() {
		Flux<String> nombres = Flux.just("Joffre As" , "Tangiro Yamaka" , "Yagami Lie" , "Mila Happy" , "Diego SANTOS" , "Felix Jantaro" ,
						"Tangiro Mosque" , "Pedro Pucho" , "Jose lan");
		Flux<Alumno> alumnos = nombres.map(nombre -> new Alumno(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase(), null,
						null, null))
				.filter(Alumno -> Alumno.getNombre().toLowerCase().equalsIgnoreCase("tangiro"))
				.doOnNext(alumno -> {
					if (alumno == null) {

						throw new RuntimeException("Nombre no pueden ser vacios");
					}
					System.out.println(alumno.getNombre().concat(" ").concat(alumno.getApellido()));
				})
				.map(alumno -> {
					String nombre = alumno.getNombre().toLowerCase();
					alumno.setNombre(nombre);
					return alumno;
				});
		alumnos.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), 
				new Runnable() {

			@Override
			public void run() {
				log.info("has finalizado la ejecuciuon del observable con exito!");
			}
			});
		
				
	}
	/*UTILIZANDO UN ARRAY LIST  PARA  HACER UNA CUNSULATA EN BASE DE DATOS RETORNARA UN LISTA DE OBJETOS  */
	public void ejemplo5() {
		List<String> usuarioList = new ArrayList<>();
		usuarioList.add("Joffre As");
		usuarioList.add("Tangiro Yamaka");
		usuarioList.add("Yagami Lie");
		usuarioList.add("Mila Happy");
		usuarioList.add("Diego SANTOS");
		usuarioList.add("Tangiro Mosque");
		usuarioList.add("Pedro Pucho");
		usuarioList.add("Jose lan");
		
		
		Flux<String> nombres = Flux.fromIterable(usuarioList);
		Flux<Alumno> alumnos = nombres.map(nombre -> new Alumno(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase(), null,
						null, null))
				.filter(Alumno -> Alumno.getNombre().toLowerCase().equalsIgnoreCase("tangiro"))
				.doOnNext(alumno -> {
					if (alumno == null) {

						throw new RuntimeException("Nombre no pueden ser vacios");
					}
					System.out.println(alumno.getNombre().concat(" ").concat(alumno.getApellido()));
				})
				.map(alumno -> {
					String nombre = alumno.getNombre().toLowerCase();
					alumno.setNombre(nombre);
					return alumno;
				});
		alumnos.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), 
				new Runnable() {

			@Override
			public void run() {
				log.info("has finalizado la ejecuciuon del observable con exito!");
			}
			});
		
				
	}
	/*OPERADOR FLATMAP combierte a otro flujo mono o flux  */
	public void ejemplo6() {
		
		
		List<String> usuariosList = new ArrayList<>();
		usuariosList.add("Joffre As");
		usuariosList.add("Tangiro Yamaka");
		usuariosList.add("Yagami Lie");
		usuariosList.add("Mila Happy");
		usuariosList.add("Diego SANTOS");
		usuariosList.add("Tangiro Mosque");
		usuariosList.add("Pedro Pucho");
		usuariosList.add("Jose lan");
		
		
		Flux.fromIterable(usuariosList)
				.map(nombre -> new Alumno(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase(), null, null, null))
				.flatMap(alumno -> {
					if (alumno.getNombre().equalsIgnoreCase("tangiro")) {
						return Mono.just(alumno);
					}else{
						return Mono.empty();
					}
				})
				
				.map(alumno -> {
					String nombre = alumno.getNombre().toLowerCase();
					alumno.setNombre(nombre);
					return alumno;
				})
		        .subscribe(u ->log.info(u.toString()));
		
				
	}
	public void ejemplo7() {
		
		
		List<Alumno> alumnosList = new ArrayList<>();
		 alumnosList.add(new Alumno(null, "Joffre", "As", null, null, null,null));		
		 alumnosList.add(new Alumno(null, "Tangiro", "Yamaka", null, null, null,null)); 
		 alumnosList.add(new Alumno(null, "Yagami", "Lie", null, null, null,null)); 
		 alumnosList.add(new Alumno(null, "Mila", "Happy", null, null, null,null));
		 alumnosList.add(new Alumno(null, "Diego", "SANTOS", null, null, null,null)); 
		 alumnosList.add(new Alumno(null, "Tangiro", "Mosque", null, null, null,null)); 
		  alumnosList.add(new Alumno(null, "Pedro", "Pucho", null, null, null,null));
		  alumnosList.add(new Alumno(null, "Jose", "lan", null, null, null,null));
		
		
		
		Flux.fromIterable(alumnosList)
				.map(nombre -> nombre.getNombre().toUpperCase().concat(" ").concat(nombre.getApellido().toUpperCase()))
				.flatMap(alumno -> {
					if (alumno.contains("tangiro".toUpperCase())) {
						return Mono.just(alumno);
					}else{
						return Mono.empty();
					}
				})
				
				.map(nombre -> {				
					return nombre.toLowerCase();
				})
		        .subscribe(n ->log.info(n.toString()));
						
	}
	
	
	public void ejemplo8() {
		
		
		List<Alumno> alumnosList = new ArrayList<>();
		 alumnosList.add(new Alumno(null, "Joffre", "As", null, null, null,null));		
		 alumnosList.add(new Alumno(null, "Tangiro", "Yamaka", null, null, null,null)); 
		 alumnosList.add(new Alumno(null, "Yagami", "Lie", null, null, null,null)); 
		 alumnosList.add(new Alumno(null, "Mila", "Happy", null, null, null,null));
		 alumnosList.add(new Alumno(null, "Diego", "SANTOS", null, null, null,null)); 
		 alumnosList.add(new Alumno(null, "Tangiro", "Mosque", null, null, null,null)); 
		  alumnosList.add(new Alumno(null, "Pedro", "Pucho", null, null, null,null));
		  alumnosList.add(new Alumno(null, "Jose", "lan", null, null, null,null));
		  
		  Flux.fromIterable(alumnosList)
		  .collectList()
		  .subscribe(lista ->{ 
		  lista.forEach(item -> log.info(item.toString()));
		  });
	}
		  	
			
				
		  public void ejemplo9() {
			 
		      Mono<Alumno> alumnoMono = Mono.fromCallable(()-> new Alumno(null, "Yagami", "Lie", null, null, null,null));
		      Mono<Comentarios> comentariosAlumnoMono = Mono.fromCallable(()->{
		    	  Comentarios comentarios = new Comentarios();
		    	  comentarios.addComentarios("¡hola Yagami, que tal!");
		    	  comentarios.addComentarios("¡alumno registrado!");
		    	  comentarios.addComentarios("¡alumno registrado!");
		    	  return comentarios;
		      });
		      alumnoMono.flatMap(a -> comentariosAlumnoMono.map(c -> new AlumnoComentarios(a, c)))
		      .subscribe(ac -> log.info(ac.toString()));
		  }
		  
		  public void ejemplo10() {
			  
		      Mono<Alumno> alumnoMono = Mono.fromCallable(()-> new Alumno(null, "Yagami", "Lie", null, null, null,null));
		      Mono<Comentarios> comentariosAlumnoMono = Mono.fromCallable(()->{
		    	  Comentarios comentarios = new Comentarios();
		    	  comentarios.addComentarios("¡hola Yagami, que tal!");
		    	  comentarios.addComentarios("¡alumno registrado!");
		    	  comentarios.addComentarios("¡alumno buenas suerte!");
		    	  return comentarios;
		      });
		      Mono<AlumnoComentarios> alumnoConComentario = alumnoMono
		    		  .zipWith(comentariosAlumnoMono, (alumno, comentariosAlumno) -> new AlumnoComentarios(alumno,comentariosAlumno));
		               alumnoConComentario.subscribe(ac -> log.info(ac.toString()));
			  
			  
			  
		  }
		  public void ejemplo11() {
			  
		      Mono<Alumno> alumnoMono = Mono.fromCallable(()-> new Alumno(null, "Yagami", "Lie", null, null, null,null));
		      Mono<Comentarios> comentariosAlumnoMono = Mono.fromCallable(()->{
		    	  Comentarios comentarios = new Comentarios();
		    	  comentarios.addComentarios("¡hola Yagami, que tal!");
		    	  comentarios.addComentarios("¡alumno registrado!");
		    	  comentarios.addComentarios("¡alumno buenas suerte!");
		    	  return comentarios;
		      });
		      Mono<AlumnoComentarios> alumnoConComentario = alumnoMono
		    		  .zipWith(comentariosAlumnoMono)
		    		  .map(tuple ->{
		    			  Alumno a = tuple.getT1();
		    			  Comentarios c =tuple.getT2();
		    			  return new AlumnoComentarios(a,c);
		    			  
		    		  });
		               alumnoConComentario.subscribe(ac -> log.info(ac.toString()));
			  
			  
			  
		  }
		  public void ejemplo12() {
			  /*RANGO*/
		      Flux.just(1, 2, 3, 4)
		      .map(i-> (i*2))
		      .zipWith(Flux.range(0, 4), (uno, dos) -> String.format("Primer flux: %d, Segundo flux: %d", uno,dos))
		      .subscribe(texto -> log.info(texto));			  
		      }
			}
	