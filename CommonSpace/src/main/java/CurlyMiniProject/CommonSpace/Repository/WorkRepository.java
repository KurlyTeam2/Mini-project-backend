package CurlyMiniProject.CommonSpace.Repository;

import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {
    @Query("SELECT w FROM Work w "
                + "INNER JOIN FETCH w.user u "
                + "WHERE u.Id = :personId")
    List<Work> findByUserId(@Param("personId") Long personId);

    @Query("SELECT w FROM Work w "
                + "INNER JOIN FETCH w.user u "
                + "WHERE u.name = :personName")
    List<Work> findByUserName(@Param("personName") String personName);
}
