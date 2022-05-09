package CurlyMiniProject.CommonSpace.Controller;

import CurlyMiniProject.CommonSpace.DTO.Default.DefaultResponse;
import CurlyMiniProject.CommonSpace.DTO.Default.ResponseMessage;
import CurlyMiniProject.CommonSpace.DTO.Default.StatusCode;
import CurlyMiniProject.CommonSpace.DTO.Request.UserCreateRequest;
import CurlyMiniProject.CommonSpace.DTO.Request.UserUpdateRequest;
import CurlyMiniProject.CommonSpace.DTO.Domain.UserDTO;
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
            ResponseEntity response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
            return response;
        }
        List<UserDTO> data = users.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        ResponseEntity response = new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_USER, data)
                , HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            ResponseEntity response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
            return response;
        }
        UserDTO data = new UserDTO(user.get());
        ResponseEntity response = new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_USER, data),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserByName(@PathVariable String name) {
        List<User> users = userService.findByName(name);
        if (users.isEmpty()) {
            ResponseEntity response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
            return response;
        }
        List<UserDTO> data = users.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        ResponseEntity response = new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_USER, data),
                HttpStatus.OK
        );
        return response;
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody UserCreateRequest req){
        User user = new User();
        user.setName(req.getName());
        user.setUserId(req.getId());
        user.setPassword(req.getPassword());
        user.setEntryDate(LocalDate.now());
        user.setAdmin(false);
        userService.save(user);
        ResponseEntity response = new ResponseEntity(
                DefaultResponse.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, null),
                HttpStatus.CREATED
        );
        return response;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putUser(@PathVariable Long id, @RequestBody UserUpdateRequest data) {
        User beforeUser = userService.findById(id).get();

        ResponseEntity response = null;

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
            return response;
        } else {
            response = new ResponseEntity(
                    DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.UPDATED_USER_FAIL, null),
                    HttpStatus.BAD_REQUEST
            );
            return response;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        ResponseEntity response = new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.DELETE_USER, null),
                HttpStatus.OK
        );
        return response;
    }
}