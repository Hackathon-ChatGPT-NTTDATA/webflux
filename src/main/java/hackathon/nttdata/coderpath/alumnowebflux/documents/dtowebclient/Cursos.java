package hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cursos {

	private String id;

	private String nombre;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

}
