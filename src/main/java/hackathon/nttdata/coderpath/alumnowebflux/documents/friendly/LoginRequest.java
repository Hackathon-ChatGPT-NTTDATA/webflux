package hackathon.nttdata.coderpath.alumnowebflux.documents.friendly;

import lombok.Data;

@Data
public class LoginRequest {
	
    private String email;
    private String password;
    private String code;

}
