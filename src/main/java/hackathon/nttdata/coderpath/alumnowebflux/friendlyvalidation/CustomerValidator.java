package hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation;



import org.springframework.validation.Validator;

import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models.CustomerModel;
import reactor.core.publisher.Mono;


public class CustomerValidator implements BaseValidator<CustomerModel> {
	
    private final Validator<CustomerModel> validator;
    
    public CustomerValidator(){
        validator = ValidatorBuilder.of(CustomerModel.class)
                .constraint(CustomerModel::getCompanyEmail, "companyEmail", c-> c.notNull().email())
                .constraint(CustomerModel::getCompanyName, "companyName", c -> c.notNull())
                .constraint(CustomerModel::getTaxId, "taxId", c ->  c.pattern(""))
                .build();
    }

    @Override
    public Mono<CustomerModel> validate(CustomerModel model) {
        ConstraintViolations violations = validator.validate(model);
        if (violations.isValid()) {
            return Mono.just(model);
        } else {
            return Mono.error(new ValidationException(violations.violations()));
        }
    }
	
	
	

}
