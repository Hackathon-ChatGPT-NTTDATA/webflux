package hackathon.nttdata.coderpath.alumnowebflux.documents;


public class AlumnoComentarios {
	
	private  Alumno alumno;
	private Comentarios comentarios;
	public AlumnoComentarios(Alumno alumno, Comentarios comentarios) {
		
		this.alumno = alumno;
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "AlumnoComentarios [alumno=" + alumno + ", comentarios=" + comentarios + "]";
	}
	
}
