package hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product-chatgpt-hackathon-nttdata")
public class Product {

	@Id
	private int id;
	private String name;
	private float price;

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
	}

}
