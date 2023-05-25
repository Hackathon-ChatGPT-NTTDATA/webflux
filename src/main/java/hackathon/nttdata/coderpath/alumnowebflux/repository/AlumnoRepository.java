package hackathon.nttdata.coderpath.alumnowebflux.repository;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends ReactiveMongoRepository<Alumno, String> {

}
