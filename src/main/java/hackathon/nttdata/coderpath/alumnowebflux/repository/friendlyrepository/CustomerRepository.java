package hackathon.nttdata.coderpath.alumnowebflux.repository.friendlyrepository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models.CustomerModel;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerModel, String>{

}
