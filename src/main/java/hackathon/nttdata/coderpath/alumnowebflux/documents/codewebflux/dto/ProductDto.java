package hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

	 @NotBlank(message = "name is mandatory")
	    private String name;
	    @Min(value = 1, message = "price must be greater then zero")
	    private float price;
}
