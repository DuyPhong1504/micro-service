package Phong.gatewayservice.controller;

import Phong.gatewayservice.dto.UserDto;
import Phong.gatewayservice.dto.response.UserResponseDto;
import Phong.gatewayservice.entity.User;
import Phong.gatewayservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/signup")
    public User signup(@RequestBody UserDto userDto){
        return service.signup(userDto);
    }

    @PostMapping("/signing")
    public UserResponseDto signing(@RequestBody UserDto userDto){
        return service.signing(userDto);
    }

}
