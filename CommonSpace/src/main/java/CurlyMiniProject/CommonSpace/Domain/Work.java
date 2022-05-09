package CurlyMiniProject.CommonSpace.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Work implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "date")
    private LocalDate workDate;

    @Column(name = "working_time")
    private LocalDateTime workingTime;

    @Column(name = "quitting_time")
    private LocalDateTime quittingTime;

    @Column(name = "total_work_time")
    private LocalTime totalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private User user;
}
