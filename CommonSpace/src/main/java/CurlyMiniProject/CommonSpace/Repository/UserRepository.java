package CurlyMiniProject.CommonSpace.Repository;

import CurlyMiniProject.CommonSpace.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * userId로 user 조회
     * @param userId : 조회하고자 하는 user의 user_id
     */
    @Query("SELECT u from User u "
            + "WHERE u.userId = :userId")
    Optional<User> findByUserId(@Param("userId") String userId);

    /**
     * name으로 user 조회
     * @param name : 조회하고자 하는 user person_name
     */
    @Query("SELECT u from User u "
            + "WHERE u.name = :name")
    List<User> findByName(@Param("name") String name);

    /**
     * id로 user 조회
     * Work FETCH join
     * @param id : 조회하고자 하는 user ID(PK)
     */
    @Query("SELECT u FROM User u "
            + "INNER JOIN FETCH u.works w "
            + "WHERE w.Id = :id")
    Optional<User> findByIdWithWork(@Param("id") Long id);

    /**
     * name으로 user 조회합니다.
     * Work FETCH join
     * @param name : 조회하고자 하는 user person_name
     */
    @Query("SELECT u FROM User u "
            + "INNER JOIN FETCH u.works w "
            + "WHERE u.name = :name")
    Optional<User> findByNameWithWork(@Param("name") String name);
}
