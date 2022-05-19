package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);
    List<User> findByName(String name);
    Optional<User> findByIdWithWork(Long id);
    Optional<User> findByNameWithWork(String name);
    void save(User user);
    void deleteById(Long id);
}
