package CurlyMiniProject.CommonSpace;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableEncryptableProperties
@Configuration
public class JasyptConfig {
    public static final String JASYPT_STRING_ENCRYPTOR = "jasyptStringEncryptor";

}
