package fr.uge.lootin.back.config;

import fr.uge.lootin.back.filters.JwtRequestFilter;
import fr.uge.lootin.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().cors().disable().authorizeRequests()

                .antMatchers("/bonjour").permitAll()
                .antMatchers("/picture").permitAll()
                .antMatchers("/secured/*").permitAll()
                .antMatchers("/secured/**").permitAll()

                .antMatchers(HttpMethod.GET, "/matches/test").permitAll() //


                .antMatchers("/ws/user").permitAll() //
                .antMatchers(HttpMethod.POST, "/ws/user").permitAll() //

                .antMatchers("/user").permitAll() //
                .antMatchers(HttpMethod.POST, "/user").permitAll() //
                // No need authentication.
                .antMatchers("/login").permitAll() //
                .antMatchers(HttpMethod.POST, "/login").permitAll() //

                // No need authentication.
                .antMatchers("/register").permitAll() //
                .antMatchers(HttpMethod.POST, "/register").permitAll() //

                // No need authentication.
                .antMatchers(HttpMethod.GET, "/games/").permitAll() //
                .antMatchers(HttpMethod.POST, "/games/").permitAll() //

                // No need authentication.
                .antMatchers(HttpMethod.POST, "/images/upload").permitAll() //

                //.antMatchers("/bonjour").permitAll()


                // Need authentication.
                .anyRequest().authenticated()



                // Set the session security
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
