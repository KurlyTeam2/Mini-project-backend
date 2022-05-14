package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Domain.Work;
import CurlyMiniProject.CommonSpace.Repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("workService")
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public List<Work> getWorks(Long id) {
        return workRepository.findByUserId(id);
    }

    @Override
    public Work postWorks(Work work) {
        return workRepository.save(work);
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
