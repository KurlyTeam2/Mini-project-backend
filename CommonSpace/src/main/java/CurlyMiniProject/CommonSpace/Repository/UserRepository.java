package CurlyMiniProject.CommonSpace.Repository;

import CurlyMiniProject.CommonSpace.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * name으로 user를 조회합니다.
     * @param name : 조회하고자 하는 user의 person_name
     */
    List<User> findByName(@Param("name") String name);

    /**
     * id로 user를 조회합니다.
     * Work FETCH join
     * @param id
     */
    @Query("SELECT u FROM User u "
                + "INNER JOIN FETCH u.works w "
                + "WHERE w.Id = :id")
    Optional<User> findByIdWithWork(@Param("id") Long id);

    /**
     * name으로 user를 조회합니다.
     * Work FETCH join
     * @param name
     */
    @Query("SELECT u FROM User u "
                + "INNER JOIN FETCH u.works w "
                + "WHERE u.name = :name")
    Optional<User> findByNameWithWork(@Param("name") String name);
}
