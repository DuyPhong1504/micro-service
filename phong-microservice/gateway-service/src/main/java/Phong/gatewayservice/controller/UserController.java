package Phong.gatewayservice.controller;

import Phong.gatewayservice.dto.request.UserRequestDto;
import Phong.gatewayservice.dto.response.UserResponseDto;
import Phong.gatewayservice.entity.User;
import Phong.gatewayservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/signup")
    public User signup(@RequestBody UserRequestDto userRequestDto){
        return service.signup(userRequestDto);
    }

    @PostMapping("/signing")
    public UserResponseDto signing(@RequestBody UserRequestDto userRequestDto){
        return service.signing(userRequestDto);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUSer(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("Email: {}", authentication.getName());
//        authentication.getAuthorities().forEach(
//                grantedAuthority -> log.info(grantedAuthority.getAuthority())
//        );
        System.out.print("Username" + authentication);



        return service.getAllUser();
    }

}
