package CurlyMiniProject.CommonSpace.DTO.Request;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
public class WorkCreateRequest {
    private LocalDateTime workingTime;
    private LocalDateTime quittingTime;
    private LocalTime totalTime;
}
