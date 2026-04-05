package spring_group1.com.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserResponse {

    private Integer userId;
    private String username;
    private String email;
    private String profileImg;
    private Boolean isVerified;
    private Integer xp;
    private Integer level;
}
