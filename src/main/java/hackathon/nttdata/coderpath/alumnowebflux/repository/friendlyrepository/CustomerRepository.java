package hackathon.nttdata.coderpath.alumnowebflux.repository.friendlyrepository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models.CustomerModel;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerModel, String>{

}
