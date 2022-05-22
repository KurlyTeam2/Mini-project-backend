package CurlyMiniProject.CommonSpace.DTO.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class WorkUpdateRequest {
    private Long workId;
    private LocalDateTime workingTime;
    private LocalDateTime quittingTime;
    private LocalTime totalTime;
}
