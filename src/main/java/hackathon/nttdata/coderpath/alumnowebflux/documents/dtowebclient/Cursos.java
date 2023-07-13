package hackathon.nttdata.coderpath.alumnowebflux.documents.dtowebclient;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import hackathon.nttdata.coderpath.alumnowebflux.documents.Alumno;
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

	List<Alumno> alumnos;

}
