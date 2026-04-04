package spring_group1.com.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String otp;
}
