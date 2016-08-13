package flashcards;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Created by Vitaliy on 12/21/2015.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthUserDetailsDao authUserDetailsDao;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                .antMatchers("/", "/order", "/public", "/libs").permitAll()
//                .antMatchers("/admin/**").fullyAuthenticated()
////                .anyRequest().denyAll()
//                .and()
//                .formLogin()
//                //.successHandler() TODO
//                .loginPage("/loginAdmin")
//                .permitAll()
//
//                .and()
//                .logout()
//                .permitAll()
//                .and()
                .csrf()
                .disable();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(authUserDetailsDao);
//    }

    @Bean
    public GoogleIdTokenVerifier verifier() {
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        return new GoogleIdTokenVerifier.Builder(transport, jsonFactory).build();
    }


}
