package com.sophossolutions.programacion.reactiva.seguimiento1.config.security;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    JWTFilter jwsFilter;

    public SecurityConfiguration(JWTFilter jwsFilter) {
        this.jwsFilter = jwsFilter;
    }

    private final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.authorizeExchange( authorize -> authorize
                .pathMatchers("/menu/**").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/cliente/**").hasRole("ADMIN")
                        .anyExchange().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return serverHttpSecurity.build();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityWebFilterChain securityWebFilterChainJWT(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity.securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"))
                .authorizeExchange( authorize -> authorize
                        .anyExchange().authenticated())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .addFilterAt(jwsFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    public List<User> userList(){
        List<User> list = new ArrayList<>();
        list.add(User.builder()
                .id(1)
                .username("carlos")
                .password("password")
                .role("ADMIN")
                .build());
        list.add(User.builder()
                .id(2)
                .username("Daniel")
                .password("password")
                .role("USER")
                .build());
        list.add(User.builder()
                .id(3)
                .username("Maria")
                .password("1234")
                .role("USER")
                .build());
        return list;
    }

    @Bean
    public MapReactiveUserDetailsService setUserDetailService(){
        return new MapReactiveUserDetailsService(this.userList().stream().map(user -> org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .passwordEncoder(ENCODER::encode)
                .build()).toList());
    }
}
