package Phong.gatewayservice.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String role;
}
