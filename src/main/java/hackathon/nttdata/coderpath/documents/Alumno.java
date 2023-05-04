package hackathon.nttdata.coderpath.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import java.util.Date;



@Data
@Document(collection = "alumno-chatgpt-hackathon-nttdata")
public class Alumno {

	@Id
	private String id;

	private String nombre;

	private String apellido;

	private String email;


	private String rutafoto;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

}
