package Phong.gatewayservice.config;

import Phong.gatewayservice.dto.response.UserResponseDto;
import Phong.gatewayservice.entity.User;
import Phong.gatewayservice.enums.Role;
import Phong.gatewayservice.repository.UserRepository;
import Phong.gatewayservice.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;


    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByEmail("admin@gmail.com").isEmpty()){
                String hashPassword = passwordEncoder.encode("admin");
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder().
                        userName("admin")
                        .password(hashPassword)
                        .email("admin@gmail.com")
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user created by default password");
            }
        };
    }
}
