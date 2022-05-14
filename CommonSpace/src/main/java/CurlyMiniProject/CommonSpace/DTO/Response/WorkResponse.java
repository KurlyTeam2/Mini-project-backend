package CurlyMiniProject.CommonSpace.DTO.Response;

import CurlyMiniProject.CommonSpace.Domain.Work;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
public class WorkResponse {
    private LocalDate workDate;
    private LocalDateTime workingTime;
    private LocalDateTime quittingTime;
    private LocalTime totalTime;

    public WorkResponse(Work w) {
        workDate = w.getWorkDate();
        workingTime = w.getWorkingTime();
        quittingTime = w.getQuittingTime();
        totalTime = w.getTotalTime();
    }
}
