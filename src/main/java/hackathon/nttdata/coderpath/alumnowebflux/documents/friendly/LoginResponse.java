package hackathon.nttdata.coderpath.alumnowebflux.documents.friendly;

import lombok.Data;
import lombok.Value;

@Data
public class LoginResponse {
	
    private String userId;
    private String token;

}
