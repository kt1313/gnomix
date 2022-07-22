package pl.clockworkjava.gnomix.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{

    private PasswordEncoder encoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder encoder){
        this.encoder=encoder;
    }
    @Override
    protected  void configure (HttpSecurity http) throws Exception{
        http
//                .csrf().disable()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/v3/api-docs/*")
                .permitAll()
//                .antMatchers(HttpMethod.GET, "/api/rooms/**")
//                .hasAnyRole("MANAGER", "RECEPTION")
//                .antMatchers("api/rooms/**", "/rooms/**")
//                .hasRole("MANAGER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/rooms", true)
                .and()
                .rememberMe();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("pawelcwik")
                .password(this.encoder.encode("pawelcwik"))
                .roles("MANAGER").build();

        UserDetails user2 = User.builder()
                .username("ali")
                .password(this.encoder.encode("ali"))
                .roles("RECEPTION").build();
        return new InMemoryUserDetailsManager(user);
    }

}
