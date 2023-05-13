package edu.ucentral.servicio.zuul.oauth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/estudiantes","/api/grupos","/api/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, "/api/estudiantes/pagina","/api/grupos/pagina","/api/usuarios/pagina").permitAll()
		.antMatchers(HttpMethod.GET, "/api/estudiantes/{id}","/api/grupos/{id}","/api/usuarios/{id}").hasAnyRole("ADMIN","USER")
		.antMatchers("/api/estudiantes/**","/api/grupos/**","/api/usuarios/**").hasRole("ADMIN")	
		.antMatchers(HttpMethod.GET, "/api/pasajeros","/api/psqr","/api/tramites").permitAll()
		.antMatchers(HttpMethod.GET, "/api/pasajeros/pagina","/api/psqr/pagina","/api/tramites/pagina").permitAll()
		.antMatchers(HttpMethod.GET, "/api/pasajeros/{id}","/api/psqr/{id}","/api/tramites/{id}").permitAll()
		//.antMatchers("/api/pasajeros/**","/api/psqr/**","/api/tramites/**").hasRole("ADMIN")		
		.antMatchers(HttpMethod.GET, "/api/psqr/motivos").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/psqr/{id}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/psqr/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/psqr/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/pasajeros/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/pasajeros/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/pasajeros/{id}").permitAll()
		
		.antMatchers(HttpMethod.POST, "/api/tramites/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/tramites/{id}").permitAll()
		.anyRequest().authenticated();
		
		
		
	}

	@Bean
	
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("valor_codigo_secreto");
		return tokenConverter;
	}
	
}
