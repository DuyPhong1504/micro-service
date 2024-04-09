package Phong.gatewayservice.service;

import Phong.gatewayservice.dto.UserDto;
import Phong.gatewayservice.dto.response.UserResponseDto;
import Phong.gatewayservice.entity.User;
import Phong.gatewayservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    public User signup(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashPassword);
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    public UserResponseDto signing(UserDto userDto) {
        User user = new User();
        Optional<User> findUser = userRepository.findByEmail(userDto.getEmail());
        if (findUser.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(userDto.getPassword(), findUser.get().getPassword())) {
                String token = authenticationService.generateToken(userDto.getEmail());
                UserResponseDto result = new UserResponseDto();
                result.setAuthenticated(true);
                result.setToken(token);
                return result;
            }
        }
        throw new IllegalArgumentException("email or password wrong!!");
    }
}
