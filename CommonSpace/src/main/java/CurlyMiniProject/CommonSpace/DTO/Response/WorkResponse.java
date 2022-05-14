package CurlyMiniProject.CommonSpace.DTO.Response;

import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Domain.Work;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
public class WorkResponse {

    private Long Id;
    private LocalDateTime workingTime;
    private LocalDateTime quittingTime;
    private LocalTime totalTime;
    private LocalDate workDate;
    private Long userId;
    private String userName;

    public WorkResponse(Work work) {
        Id = work.getId();
        workingTime = work.getWorkingTime();
        quittingTime = work.getQuittingTime();
        totalTime = work.getTotalTime();
        workDate = work.getWorkDate();
        userId = work.getUser().getId();
        userName = work.getUser().getName();
    }
}
