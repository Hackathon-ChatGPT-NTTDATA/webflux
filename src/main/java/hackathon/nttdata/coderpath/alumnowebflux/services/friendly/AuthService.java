package hackathon.nttdata.coderpath.alumnowebflux.services.friendly;

import reactor.core.publisher.Mono;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.LoginRequest;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.LoginResponse;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.MFALoginRequest;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.MFASignupResponse;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.SignupRequest;
import hackathon.nttdata.coderpath.alumnowebflux.documents.friendly.SignupResponse;


public interface AuthService {
	
	Mono<SignupResponse> signup	(SignupRequest request);
	
	Mono<LoginResponse>	login ( LoginRequest request);
	
	Mono<MFASignupResponse> signupMFA (SignupRequest request);
	
	Mono<LoginResponse> loginMFA (MFALoginRequest request);
	
	Mono<String> parseToken (String token);
	

}
