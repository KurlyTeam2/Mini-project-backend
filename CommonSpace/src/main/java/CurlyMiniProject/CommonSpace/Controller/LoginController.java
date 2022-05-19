package CurlyMiniProject.CommonSpace.Controller;

import CurlyMiniProject.CommonSpace.Config.Security.JwtTokenProvider;
import CurlyMiniProject.CommonSpace.DTO.Default.DefaultResponse;
import CurlyMiniProject.CommonSpace.DTO.Default.ResponseMessage;
import CurlyMiniProject.CommonSpace.DTO.Default.StatusCode;
import CurlyMiniProject.CommonSpace.DTO.Request.LoginRequest;
import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest req) {
        User user = userService.findByUserId(req.getUserId()).get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL, null)
                    , HttpStatus.BAD_REQUEST);

        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS
                                    , jwtTokenProvider.createToken(user.getUserId(), user.getRoles()))
                , HttpStatus.OK);
    }

}
