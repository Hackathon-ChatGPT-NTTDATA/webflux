package hackathon.nttdata.coderpath.alumnowebflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import reactor.core.publisher.Flux;


@EnableEurekaClient
@SpringBootApplication
public class AlumnowebfluxApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(AlumnowebfluxApplication.class);  
	public static void main(String[] args) {
		SpringApplication.run(AlumnowebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//*Flux<String> nombres = Flux.just("Joffre", "tangiro", "Mila", "Diego", "Joffre", "tangiro", "Mila", "Diego") //*
		//*  .doOnNext(elemento -> System.out.println(elemento));//*
	   		
		//*nombres.subscribe(log::info);//*
	
		Flux<String> nombres = Flux.just("Joffre", "tangiro", "Mila", "Diego", "Joffre", "tangiro", "Mila", "Diego")
				.doOnNext(err -> {
					if(err.isEmpty()) {
						throw new RuntimeException("nombres vacios");}
					{System.out.println(err);}
					});
		nombres.subscribe(err -> log.info(err));
				
		
	}   
}
