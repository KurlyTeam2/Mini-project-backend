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
        List<WorkResponse> data = workService.getUserWorks(userId).stream()
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

    @GetMapping
    public ResponseEntity getWorks() {
        List<Work> works = workService.getWorks();
        List<WorkResponse> data = works.stream()
                .map(WorkResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.READ_WORK, data)
                , HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity putWork(@PathVariable Long userId, @RequestBody WorkUpdateRequest req) {
        User staff = userService.findById(userId).get();
        User administrator = userService.findById(req.getAdminId()).get();
        if (!administrator.isAdmin()) {
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.FORBIDDEN, ResponseMessage.NOT_ADMIN, null)
                    , HttpStatus.FORBIDDEN);
        }
        List<Work> workList = staff.getWorks();
        Work work = null;
        boolean isFound = false;
        for (int i=0; i < workList.size() ; i++) {
            if (workList.get(i).getId() == req.getWorkId()) {
                work = workList.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            work.setWorkingTime(req.getWorkingTime());
            work.setQuittingTime(req.getQuittingTime());
            work.setTotalTime(req.getTotalTime());
            workService.save(work);
            return new ResponseEntity(
                    DefaultResponse.res(StatusCode.OK, ResponseMessage.UPDATED_WORK_SUCCESS, null)
                    , HttpStatus.OK);
        }
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.UPDATED_WORK_FAIL, null)
                , HttpStatus.BAD_REQUEST);
    }
}
