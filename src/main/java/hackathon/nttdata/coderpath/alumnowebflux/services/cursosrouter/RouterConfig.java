package hackathon.nttdata.coderpath.alumnowebflux.services.cursosrouter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

import hackathon.nttdata.coderpath.alumnowebflux.controller.handler.CursoHandler;



@Configuration
public class RouterConfig {
	
	@Bean
    public RouterFunction<ServerResponse> rutas(CursoHandler handler){
        return  route(GET("/webclient"), handler::listar)
        		.andRoute(GET("/webclient/{id}"), handler::getOne)
        		.andRoute(POST("/webclient/create-cursos"), handler::save)
        		.andRoute(PUT("/webclient/update-cursos/{id}"), handler::update)
        		.andRoute(DELETE("/webclient/delete-cursos/{id}"), handler::delete);
    }

}
