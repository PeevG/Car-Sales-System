package softuni.carsalessystem.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                  .authorizeRequests()
                  // With this line we allow access to all static resources
                  .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                  // With this line we allow access to home page, login page and registration page for everyone.
                  .antMatchers("/", "/users/login", "/users/registration").permitAll()
                  // Forbid all other pages for unauthenticated users.
                  .antMatchers("/**").authenticated()
                  // Configure login with login HTML form with two fields(username, password for example).
                .and()
                  .formLogin()
                  // Login page is located at http:://<serveraddress>:<port>/users/login
                  .loginPage("/users/login")
                  // This is the name of the <input..> in the login form.
                  // The value of this input will be presented to our User details service.
                  // Those that want to name the input field differently, e.g. email may change the value below.
                  .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                  // The name of the <input..> HTML filed that keeps password
                  .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                  // The place where we should land in case that the login is successful.
                  .defaultSuccessUrl("/")
                  // The place where we should land in case that the login is NOT SUCCESSFUL.
                  .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                // This is the URL which spring will implement for me and will log the user out.
                .logoutUrl("/logout")
                // Where to go after logout.
                .logoutSuccessUrl("/")
                // Remove the session from server
                .invalidateHttpSession(true)
                // Delete the cookies that references to my session.
                .deleteCookies("JSESSIONID");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //This gives spring two important components.
        //  1. Our user details service that translates usernames, emails..etc to UserDetails
        //  2. Password encoder - the component that can decide if the user password matches
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        // registration:
        // blablapass -> password encoder -> kxzjcwqpeoizxlk123ld (hashed pass)

        // login:
        // (username, raw_password) -> passwordEncoder.matches(raw_password, hashed pass)
    }
}
