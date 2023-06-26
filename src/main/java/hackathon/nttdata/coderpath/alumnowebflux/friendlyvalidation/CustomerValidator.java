package hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation;


import hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation.ValidationException;
import hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation.BaseValidator;

import hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation.CustomerValidator;

import javax.validation.Validator;

import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models.CustomerModel;
import reactor.core.publisher.Mono;


public class CustomerValidator implements BaseValidator<CustomerModel> {

	@Override
	public Mono<CustomerModel> validate(CustomerModel t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * private final Validator<CustomerModel> validator;
	 * 
	 * public CustomerValidator(){ validator =
	 * ValidatorBuilder.of(CustomerModel.class)
	 * .constraint(CustomerModel::getCompanyEmail, "companyEmail", c->
	 * c.notNull().email()) .constraint(CustomerModel::getCompanyName,
	 * "companyName", c -> c.notNull()) .constraint(CustomerModel::getTaxId,
	 * "taxId", c -> c.pattern("")) .build(); }
	 * 
	 * @Override public Mono<CustomerModel> validate(CustomerModel model) {
	 * ConstraintViolations violations = CustomerValidator.validate(model); if
	 * (violations.isValid()) { return Mono.just(model); } else { return
	 * Mono.error(new ValidationException(violations.violations())); } }
	 */
	
	
	

}
