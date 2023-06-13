package hackathon.nttdata.coderpath.alumnowebflux.documents.friendly;

import lombok.Value;

@Value
public class MFASignupResponse {
	
    String userId;
    String secretKey;
}
