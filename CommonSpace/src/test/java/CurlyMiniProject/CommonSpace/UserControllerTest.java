package CurlyMiniProject.CommonSpace;


import CurlyMiniProject.CommonSpace.Repository.UserRepository;
import CurlyMiniProject.CommonSpace.Repository.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WorkRepository workRepository;

    @BeforeEach
    void setup() {

    }
}
