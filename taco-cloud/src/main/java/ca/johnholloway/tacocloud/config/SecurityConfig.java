package ca.johnholloway.tacocloud.config;


import ca.johnholloway.tacocloud.model.TacoUser;
import ca.johnholloway.tacocloud.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            TacoUser user = userRepository.findByUsername(username);
            if(user != null) return user;

            throw new UsernameNotFoundException("TacoUser '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(

                        authz -> authz.requestMatchers("/design", "/orders", "/orders/**").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers(toH2Console()).permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/design"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .csrf(csfr -> csfr.ignoringRequestMatchers(toH2Console()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .build();
    }




    //Create users in memory using UserDetailsService & InMemoryUserDetailsManager

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(new TacoUser(
//                "Woody",
//                encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//        ));
//
//        userList.add(new TacoUser(
//                "Buzz",
//                encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//        ));
//        return new InMemoryUserDetailsManager(userList);
//    }


}
