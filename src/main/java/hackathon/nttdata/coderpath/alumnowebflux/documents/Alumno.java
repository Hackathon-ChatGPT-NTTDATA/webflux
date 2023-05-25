package hackathon.nttdata.coderpath.alumnowebflux.documents;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


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


    private String rutafoto;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

}
