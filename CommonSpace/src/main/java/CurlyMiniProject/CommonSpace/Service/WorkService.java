package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.Work;

import java.util.List;
import java.util.Optional;

public interface WorkService {
    Optional<Work> findById(Long id);
    List<Work> getUserWorks(Long id);
    Work postWorks(Work work);
    Work putWorks(Long id, Work work);
    List<Work> getWorks();
    void save(Work work);
}
