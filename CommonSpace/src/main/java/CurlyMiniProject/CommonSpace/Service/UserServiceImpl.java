package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.DTO.Response.UserResponse;
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
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUserId(String userId) { return userRepository.findByUserId(userId); }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByIdWithWork(Long id) {
        return userRepository.findByIdWithWork(id);
    }

    @Override
    public Optional<User> findByNameWithWork(String name) {
        return userRepository.findByNameWithWork(name);
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
