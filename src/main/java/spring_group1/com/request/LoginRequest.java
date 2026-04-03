package spring_group1.com.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Email format is invalid"
    )
    @Schema(defaultValue = "@gmail.com")
    private String email;

    @Size(min = 3, message = "Password must be at least 3 characters")
    @NotBlank(message = "Password must not be blank")
    @Schema(defaultValue = "123")
    private String password;
}
