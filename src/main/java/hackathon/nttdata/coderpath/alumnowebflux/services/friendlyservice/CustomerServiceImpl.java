package hackathon.nttdata.coderpath.alumnowebflux.services.friendlyservice;



import lombok.Value;
import org.springframework.stereotype.Component;

import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models.CustomerModel;
import hackathon.nttdata.coderpath.alumnowebflux.repository.friendlyrepository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("CustomerService")
@Value
public class CustomerServiceImpl implements CustomerService {
	
    CustomerRepository customerRepository;

    @Override
    public Mono<CustomerModel> createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<CustomerModel> updateCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> removeCustomer(String customerId) {
        return customerRepository.deleteById(customerId);
    }

    @Override
    public Mono<CustomerModel> findCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Flux<CustomerModel> findAllCustomers() {
        return customerRepository.findAll();
    }

}
