package hackathon.nttdata.coderpath.alumnowebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AlumnowebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnowebfluxApplication.class, args);
	}

}
