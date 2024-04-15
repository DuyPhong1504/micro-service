package Phong.gatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoderFactory;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINT = {"/user/**", "/auth/**"};

    @Value("${jwt.signKey}")
    private String signKey;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
                        .anyExchange().authenticated()
                ).
                csrf(ServerHttpSecurity.CsrfSpec::disable)
                        .oauth2ResourceServer(oauth2 ->
                                oauth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
                        ).
                build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signKey.getBytes(),"HS512");
        return NimbusReactiveJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512).build();
    }
}
