package spring_group1.com.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {

    @NotBlank(message = "Username can't be blank!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only characters")
    @Schema(defaultValue = "username")
    private String userName;

    @NotBlank(message = "Password must not be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{6,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    @Schema(defaultValue = "******")
    private String password;

    @NotBlank(message = "Email must not be blank")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Email format is invalid"
    )
    @Schema(defaultValue = "string@gmail.com")
    private String email;

    @Schema(defaultValue = "meow.jpg")
    private String profileImg;
}
