package hackathon.nttdata.coderpath.alumnowebflux.documents.managers.frendly;





import org.springframework.stereotype.Component;



@Component("TotpManager")
public class TokenManagerImpl /* */ {
	/* @Override
    public String generateSecret() {
        SecretGenerator generator = new DefaultSecretGenerator();
        return generator.generate();
    }

    @Override
   public boolean validateCode(String code, String secret) {
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return verifier.isValidCode(secret, code);
    }*/
}
