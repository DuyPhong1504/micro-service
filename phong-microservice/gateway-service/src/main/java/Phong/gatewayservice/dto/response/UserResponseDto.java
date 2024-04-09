package Phong.gatewayservice.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private boolean authenticated;
    private String token;
}
