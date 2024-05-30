package fr.carrefour.kata.infrastructure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class SecurityConfig {
	
	@ConditionalOnProperty(prefix = "app", name = "is-secured", havingValue = "true")
	@Bean
	SecurityFilterChain securedSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll() // home
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
	@ConditionalOnProperty(prefix = "app", name = "is-secured", havingValue = "false")
	@Bean
	SecurityFilterChain notSecuredSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		var selim = User.withUsername("selim")
				.password("0000")
				.roles("USER")
				.build();
		var aziz = User.withUsername("aziz")
				.password("0000")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(selim, aziz);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // prohibited in prod; should use bcrypt or alike
	}
	
}



