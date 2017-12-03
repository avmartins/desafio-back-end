package br.com.anderson.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Classe necessria por prover autenticação e autorização
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("secret1")
				.roles("USER").and().withUser("admin1").password("secret1")
				.roles("USER", "ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/buscaFeed/**")
				.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
				.csrf().disable().headers().frameOptions().disable();
	}

}
