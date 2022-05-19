package CurlyMiniProject.CommonSpace.DTO.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    private String userId;
    private String password;
}
