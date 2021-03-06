package CurlyMiniProject.CommonSpace.Controller;

import CurlyMiniProject.CommonSpace.DTO.Default.DefaultResponse;
import CurlyMiniProject.CommonSpace.DTO.Default.ResponseMessage;
import CurlyMiniProject.CommonSpace.DTO.Default.StatusCode;
import CurlyMiniProject.CommonSpace.DTO.Request.UserCreateRequest;
import CurlyMiniProject.CommonSpace.DTO.Request.UserUpdateRequest;
import CurlyMiniProject.CommonSpace.DTO.Response.UserResponse;
import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(@RequestHeader("X-AUTH-TOKEN") String token) {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
        }
        List<UserResponse> data = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_USER, data)
                , HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserById(@RequestHeader("X-AUTH-TOKEN") String token, @PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
        }
        UserResponse data = new UserResponse(user.get());
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_USER, data),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserByName(@RequestHeader("X-AUTH-TOKEN") String token, @PathVariable String name) {
        List<User> users = userService.findByName(name);
        if (users.isEmpty()) {
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
        }
        List<UserResponse> data = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_USER, data),
                HttpStatus.OK
        );
    }

//    @PostMapping
//    public ResponseEntity postUser(@RequestBody UserCreateRequest req){
//        User user = new User();
//        user.setName(req.getName());
//        user.setUserId(req.getId());
//        user.setPassword(req.getPassword());
//        user.setEntryDate(LocalDate.now());
//        user.setAdmin(req.isAdmin());
//        userService.save(user);
//        return new ResponseEntity(
//                DefaultResponse.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, null),
//                HttpStatus.CREATED
//        );
//    }

    @PostMapping(value = "/signup")
    public ResponseEntity signup(@RequestBody UserCreateRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setUserId(req.getId());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEntryDate(LocalDate.now());
        user.setAdmin(req.getIsAdmin());
        System.out.println("isAdmin: " + String.valueOf(req.getIsAdmin()));
        userService.save(user);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, null),
                HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putUser(@PathVariable Long id, @RequestBody UserUpdateRequest data, @RequestHeader("X-AUTH-TOKEN") String token) {
        User user = userService.findById(id).get();

        ResponseEntity response;

        if (user == null) {
            response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER, null),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        if (passwordEncoder.matches(data.getCurrentPassword(), user.getPassword())) {
            String encodedNewPassword = passwordEncoder.encode(data.getNewPassword());
            user.setPassword(encodedNewPassword);
            userService.save(user);
            response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.OK, ResponseMessage.UPDATED_USER_SUCCESS, null),
                    HttpStatus.OK
            );
        } else {
            response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.UPDATED_USER_FAIL, null),
                    HttpStatus.BAD_REQUEST
            );
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id, @RequestHeader("X-AUTH-TOKEN") String token) {
        userService.deleteById(id);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.DELETE_USER, null),
                HttpStatus.OK
        );
    }
}