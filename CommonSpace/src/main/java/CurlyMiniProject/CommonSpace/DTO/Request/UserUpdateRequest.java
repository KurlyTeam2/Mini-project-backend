package CurlyMiniProject.CommonSpace.DTO.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserUpdateRequest {
    private String currentPassword;
    private String newPassword;
}
