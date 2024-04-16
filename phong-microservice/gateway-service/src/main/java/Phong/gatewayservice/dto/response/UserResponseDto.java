package Phong.gatewayservice.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private boolean authenticated;
    private String token;
    private Set<String> roles;
}
