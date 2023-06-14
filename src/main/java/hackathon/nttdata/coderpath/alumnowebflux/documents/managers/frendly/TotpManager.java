package hackathon.nttdata.coderpath.alumnowebflux.documents.managers.frendly;

public interface TotpManager {
	
    String generateSecret ();

    boolean validateCode (String code, String secret);

}
