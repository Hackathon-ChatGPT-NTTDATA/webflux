package hackathon.nttdata.coderpath.alumnowebflux.services.friendlyservice;

import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models.CustomerModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	
    Mono<CustomerModel> createCustomer (CustomerModel customer);
    
    Mono<CustomerModel> updateCustomer (CustomerModel customer);
    
    Mono<Void> removeCustomer (String customerId);
    
    Mono<CustomerModel> findCustomerById (String customerId);
    
    Flux<CustomerModel> findAllCustomers ();
}
