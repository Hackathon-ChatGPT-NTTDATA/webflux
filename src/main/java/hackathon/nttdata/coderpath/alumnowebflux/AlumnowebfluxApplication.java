package hackathon.nttdata.coderpath.alumnowebflux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import reactor.core.publisher.Flux;


@EnableEurekaClient
@SpringBootApplication
public class AlumnowebfluxApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AlumnowebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		   Flux<String> nombres = Flux.just("Joffre, Allison, Mila, Diego")
		   			.doOnNext(elemento -> System.out.println(elemento));
	   		
		   nombres.subscribe();
	}   
	

}
