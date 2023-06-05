package hackathon.nttdata.coderpath.alumnowebflux.documents;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alumno-chatgpt-hackathon-nttdata")
public class Alumno {

    @Id
    private String id;

    private String nombre;

    private String apellido;

    private String email;
    
 
	@JsonIgnore
	private byte[] foto;
	
	
    private String rutafoto;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;


	public Alumno(String nombre, String apellido, String email, String rutafoto, Date createAt) {
	
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.rutafoto = rutafoto;
		this.createAt = createAt;
	}
    
    

}
