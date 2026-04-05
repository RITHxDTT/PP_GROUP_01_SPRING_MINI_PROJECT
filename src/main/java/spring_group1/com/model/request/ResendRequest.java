package spring_group1.com.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResendRequest {

    @NotBlank
    private String email;

}
