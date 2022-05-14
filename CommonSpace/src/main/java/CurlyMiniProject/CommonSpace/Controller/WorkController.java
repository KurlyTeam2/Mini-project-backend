package CurlyMiniProject.CommonSpace.Controller;

import CurlyMiniProject.CommonSpace.DTO.Default.DefaultResponse;
import CurlyMiniProject.CommonSpace.DTO.Default.ResponseMessage;
import CurlyMiniProject.CommonSpace.DTO.Default.StatusCode;
import CurlyMiniProject.CommonSpace.DTO.Request.WorkCreateRequest;
import CurlyMiniProject.CommonSpace.DTO.Request.WorkUpdateRequest;
import CurlyMiniProject.CommonSpace.DTO.Response.WorkResponse;
import CurlyMiniProject.CommonSpace.Domain.User;
import CurlyMiniProject.CommonSpace.Domain.Work;
import CurlyMiniProject.CommonSpace.Service.UserService;
import CurlyMiniProject.CommonSpace.Service.WorkService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    @Autowired
    private WorkService workService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getWorkByUserId(@PathVariable Long userId) {
        User user = userService.findById(userId).get();
        List<WorkResponse> data = workService.getWorks(userId).stream()
                .map(WorkResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_WORK, data)
                , HttpStatus.OK);
    }

    @PostMapping(value="/{userId}")
    public ResponseEntity postWork(@PathVariable Long userId, @RequestBody WorkCreateRequest req) {
        Optional<User> user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, null)
                    , HttpStatus.NOT_FOUND);
        }
        Work work = new Work();
        work.setWorkDate(LocalDate.now());
        work.setWorkingTime(req.getWorkingTime());
        work.setQuittingTime(req.getQuittingTime());
        work.setTotalTime(req.getTotalTime());
        work.setUser(user.get());
        workService.postWorks(work);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.CREATED, ResponseMessage.CREATED_WORK, null)
                , HttpStatus.CREATED);
    }

//    @PutMapping(value = "/{userId}")
//    public ResponseEntity putWork(@PathVariable Long userId, @RequestBody WorkUpdateRequest req) {
//        User user = userService.findById(userId).get();
//        if (user == null) {
//            return new ResponseEntity(
//                    DefaultResponse.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, null)
//                    , HttpStatus.NOT_FOUND);
//        }
//
//    }
//
//    @GetMapping
//    public ResponseEntity getWorks() {
//        return
//    }
}
