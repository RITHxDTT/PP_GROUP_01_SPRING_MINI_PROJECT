package spring_group1.com.request;

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
    @NotBlank(message = "user can't be blank ! ")
    @Schema(defaultValue = "rith")
    private String  userName;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 3, message = "Password must be at least 3 characters")
    @Schema(defaultValue = "******")
    private String password;
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Email format is invalid"
    )
    @Schema(defaultValue = "rith@gmail.com")
    private String email;
    @Schema(defaultValue = "meow.jpg")
    private String profileImg;
}
