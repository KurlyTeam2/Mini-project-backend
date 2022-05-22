package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.Work;
import CurlyMiniProject.CommonSpace.Repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("workService")
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public Optional<Work> findById(Long id) { return workRepository.findById(id); }

    @Override
    public List<Work> getUserWorks(Long id) {
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
    public List<Work> getWorks() {
        return workRepository.findAll(Sort.by(Sort.Direction.ASC, "user"));
    }

    @Override
    public void save(Work work) {
        workRepository.save(work);
    }
}
