package hackathon.nttdata.coderpath.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import hackathon.nttdata.coderpath.documents.Alumno;

@Repository
public interface AlumnoRepository extends ReactiveMongoRepository<Alumno, String> {

}
