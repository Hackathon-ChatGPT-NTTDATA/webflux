package hackathon.nttdata.coderpath.alumnowebflux.documents.friendly;

import lombok.Value;

@Value
public class MFALoginRequest {

    String email;
    String password;
    String code;
}
