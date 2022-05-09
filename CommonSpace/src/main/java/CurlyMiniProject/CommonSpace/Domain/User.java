package CurlyMiniProject.CommonSpace.Domain;

import lombok.*;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "person_name")
    @Size(max = 10)
    private String name;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "user_id")
    @NotEmpty
    private String userId;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> works;
}
