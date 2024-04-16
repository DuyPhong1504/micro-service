package Phong.gatewayservice.service;

import Phong.gatewayservice.dto.request.UserRequestDto;
import Phong.gatewayservice.dto.response.UserResponseDto;
import Phong.gatewayservice.entity.User;
import Phong.gatewayservice.enums.Role;
import Phong.gatewayservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    private final PasswordEncoder passwordEncoder;

    public User signup(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        String hashPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(hashPassword);
        user.setEmail(userRequestDto.getEmail());
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public UserResponseDto signing(UserRequestDto userRequestDto) {
        User user = new User();
        Optional<User> findUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (findUser.isPresent() &&
                passwordEncoder.matches(userRequestDto.getPassword(), findUser.get().getPassword())) {
            String token = authenticationService.generateToken(findUser.get());
            UserResponseDto result = new UserResponseDto();
            result.setAuthenticated(true);
            result.setToken(token);
            result.setRoles(findUser.get().getRoles());
            return result;
        }
        throw new IllegalArgumentException("email or password wrong!!");
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
