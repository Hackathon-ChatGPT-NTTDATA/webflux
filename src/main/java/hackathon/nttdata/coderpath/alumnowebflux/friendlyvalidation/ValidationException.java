package hackathon.nttdata.coderpath.alumnowebflux.friendlyvalidation;

import java.util.List;

import jakarta.validation.ConstraintViolation;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class ValidationException extends RuntimeException{
	
    @Getter
    final List<ConstraintViolation> errors;

}
