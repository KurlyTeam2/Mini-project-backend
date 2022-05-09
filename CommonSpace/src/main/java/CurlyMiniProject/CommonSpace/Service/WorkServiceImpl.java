package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Domain.Work;
import CurlyMiniProject.CommonSpace.Repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("workService")
public class WorkServiceImpl implements WorkService {

    private WorkRepository workRepository;
    private UserService userService;

    @Override
    public List<Work> getWorks(Long id) {
        List<Work> result = workRepository.findByUser_Id(id);
        return result;
    }

    @Override
    public Work postWorks(Work work) {
        Work result = workRepository.save(work);
        return result;
    }

    @Override
    public Work putWorks(Long id, Work work) {

        return null;
    }

    @Override
    public List<User> getUserWorks() {
        return null;
    }
}
