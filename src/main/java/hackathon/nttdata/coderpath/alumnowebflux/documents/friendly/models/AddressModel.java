package hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class AddressModel {

	String addressLine;
	String postalCode;
	String city;

	@JsonCreator
	public AddressModel(
			@JsonProperty("addressLine") String adressLine,
			@JsonProperty("postalCode") String postalCode,
			@JsonProperty("city") String city) {
        this.addressLine = adressLine;
        this.postalCode = postalCode;
        this.city = city;
		
	}

}
