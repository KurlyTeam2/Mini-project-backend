package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkService workService;

    @Override
    public List<User> findAll() {
        List<User> results = userRepository.findAll();
        return results;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return Optional.ofNullable(result.orElse(null));
    }

    @Override
    public List<User> findByName(String name) {
        List<User> results = userRepository.findByName(name);
        return results;
    }

    @Override
    public Optional<User> findByIdWithWork(Long id) {
        Optional<User> result = userRepository.findByIdWithWork(id);
        return Optional.ofNullable(result.orElse(null));
    }

    @Override
    public Optional<User> findByNameWithWork(String name) {
        Optional<User> result = userRepository.findByNameWithWork(name);
        return Optional.ofNullable(result.orElse(null));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
