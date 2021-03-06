package CurlyMiniProject.CommonSpace.DTO.Domain;

import CurlyMiniProject.CommonSpace.Domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
public class UserDTO {
    private Long Id;
    private String name;
    private LocalDate entryDate;
    private String userId;
    private boolean isAdmin;
    private List<WorkDTO> works;

    public UserDTO(User u) {
        Id = u.getId();
        name = u.getName();
        entryDate = u.getEntryDate();
        userId = u.getUserId();
        isAdmin = u.isAdmin();
        works = u.getWorks().stream().map(WorkDTO::new).collect(Collectors.toList());
    }
}
