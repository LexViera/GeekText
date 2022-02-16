package group15.RestServicewMongoDB;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
  
    /*
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http
        .requiresChannel(channel -> 
            channel.anyRequest().requiresSecure())
        .authorizeRequests(authorize -> authorize.anyRequest().permitAll())
        .build();
      }
      */
    @Override
      protected void configure(HttpSecurity http) throws Exception {
           http.csrf().disable();
           http.authorizeRequests().anyRequest().permitAll();
    }

    
  
  }
