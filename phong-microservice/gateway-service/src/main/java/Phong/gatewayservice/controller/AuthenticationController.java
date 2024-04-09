package Phong.gatewayservice.controller;

import Phong.gatewayservice.dto.request.VerifyRequestDto;
import Phong.gatewayservice.dto.response.VerifyResponseDto;
import Phong.gatewayservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/verify")
    VerifyResponseDto verify(@RequestBody VerifyRequestDto verifyRequestDto) throws ParseException, JOSEException {
        return authenticationService.verifyToken(verifyRequestDto);
    }
}
