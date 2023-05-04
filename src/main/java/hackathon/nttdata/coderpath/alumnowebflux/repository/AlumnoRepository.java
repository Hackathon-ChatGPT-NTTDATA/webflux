package hackathon.nttdata.coderpath.alumnowebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;

@Repository
public interface AlumnoRepository extends ReactiveMongoRepository<Alumno, String> {

}
