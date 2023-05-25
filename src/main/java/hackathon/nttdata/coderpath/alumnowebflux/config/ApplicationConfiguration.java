package hackathon.nttdata.coderpath.alumnowebflux.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
@RefreshScope
@Getter
@Setter
public class ApplicationConfiguration {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;
}
