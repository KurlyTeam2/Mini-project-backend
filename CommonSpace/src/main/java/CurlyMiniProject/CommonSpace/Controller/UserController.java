package CurlyMiniProject.CommonSpace.Controller;

import CurlyMiniProject.CommonSpace.DTO.Default.DefaultResponse;
import CurlyMiniProject.CommonSpace.DTO.Default.ResponseMessage;
import CurlyMiniProject.CommonSpace.DTO.Default.StatusCode;
import CurlyMiniProject.CommonSpace.DTO.Request.UserCreateRequest;
import CurlyMiniProject.CommonSpace.DTO.Request.UserUpdateRequest;
import CurlyMiniProject.CommonSpace.DTO.Response.UserResponse;
import CurlyMiniProject.CommonSpace.DTO.Response.UserWithWorkResponse;
import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers() {
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
    public ResponseEntity getUserById(@PathVariable Long id) {
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
    public ResponseEntity getUserByName(@PathVariable String name) {
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

    @PostMapping
    public ResponseEntity postUser(@RequestBody UserCreateRequest req){
        User user = new User();
        user.setName(req.getName());
        user.setUserId(req.getId());
        user.setPassword(req.getPassword());
        user.setEntryDate(LocalDate.now());
        user.setAdmin(req.isAdmin());
        userService.save(user);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, null),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putUser(@PathVariable Long id, @RequestBody UserUpdateRequest data) {
        User beforeUser = userService.findById(id).get();

        ResponseEntity response;

        if (beforeUser == null) {
            response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_USER, null),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }

        if (beforeUser.getPassword().equals(data.getCurrentPassword())) {
            beforeUser.setPassword(data.getNewPassword());
            userService.save(beforeUser);
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
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.DELETE_USER, null),
                HttpStatus.OK
        );
    }
}