package CurlyMiniProject.CommonSpace.DTO.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserCreateRequest {
    private String id;
    private String password;
    private String name;
    private Boolean isAdmin;
}
