package hackathon.nttdata.coderpath.alumnowebflux.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //
@ConfigurationProperties
@RefreshScope
@Getter
@Setter
public class ApplicationConfiguration extends org.springframework.web.cors.CorsConfiguration {

	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	@Value("${config.base.endpoint}")
	private String urlCursos;

	@Bean
	public WebClient registrarWebClient() {

		return WebClient.create(urlCursos);

	}

	/*
	 * @Bean public CorsWebFilter corsWebFilter() {
	 * 
	 * final CorsConfiguration corsConfig = new CorsConfiguration();
	 * corsConfig.setAllowedOrigins(Collections.singletonList(
	 * "http://localhost:4200")); corsConfig.setMaxAge(3600L);
	 * corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	 * corsConfig.addAllowedHeader("Content-Type");
	 * 
	 * final UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * corsConfig);
	 * 
	 * return new CorsWebFilter(source); }
	 */

	/*
	 * @Bean public WebMvcConfigurer corsConfig() { return new WebMvcConfigurer() {
	 * 
	 * public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedOrigins("http://localhost:4200")
	 * .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(),
	 * HttpMethod.DELETE.name()) .allowedHeaders(HttpHeaders.CONTENT_TYPE,
	 * HttpHeaders.AUTHORIZATION); } }; }
	 */

}
