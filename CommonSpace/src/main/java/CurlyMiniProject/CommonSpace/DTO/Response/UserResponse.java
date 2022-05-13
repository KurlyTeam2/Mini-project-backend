package CurlyMiniProject.CommonSpace.DTO.Response;

import CurlyMiniProject.CommonSpace.Domain.User;

import java.time.LocalDate;

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
