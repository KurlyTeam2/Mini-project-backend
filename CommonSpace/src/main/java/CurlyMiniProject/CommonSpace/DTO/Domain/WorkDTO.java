package CurlyMiniProject.CommonSpace.DTO.Domain;

import CurlyMiniProject.CommonSpace.Domain.Work;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class WorkDTO {
    private Long Id;
    private LocalDateTime workingTime;
    private LocalDateTime quittingTime;
    private LocalTime total_time;
    private LocalDate workDate;
    private Long userId;
    private String userName;

    public WorkDTO(Work work) {
        Id = work.getId();
        workingTime = work.getWorkingTime();
        quittingTime = work.getQuittingTime();
        total_time = work.getTotalTime();
        workDate = work.getWorkDate();
        userId = work.getUser().getId();
        userName = work.getUser().getName();
    }
}
