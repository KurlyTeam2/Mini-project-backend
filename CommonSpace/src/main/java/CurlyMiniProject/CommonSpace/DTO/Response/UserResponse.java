package CurlyMiniProject.CommonSpace.DTO.Response;

import CurlyMiniProject.CommonSpace.Domain.User;
import lombok.Data;
import lombok.Getter;

import javax.annotation.sql.DataSourceDefinitions;
import java.time.LocalDate;

@Data
@Getter
public class UserResponse {
    private Long Id;
    private String name;
    private LocalDate entryDate;
    private String userId;
    private boolean isAdmin;

    public UserResponse(User u) {
        Id = u.getId();
        name = u.getName();
        entryDate = u.getEntryDate();
        userId = u.getUserId();
        isAdmin = u.isAdmin();
    }
}
