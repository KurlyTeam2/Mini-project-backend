package CurlyMiniProject.CommonSpace.Service;

import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Domain.Work;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface WorkService {
    List<Work> getWorks(Long id);
    Work postWorks(Work work);
    Work putWorks(Long id, Work work);
    List<User> getUserWorks();
}
