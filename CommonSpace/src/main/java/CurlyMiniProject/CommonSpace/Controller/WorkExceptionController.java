package CurlyMiniProject.CommonSpace.Controller;

import CurlyMiniProject.CommonSpace.DTO.Default.DefaultResponse;
import CurlyMiniProject.CommonSpace.DTO.Default.ResponseMessage;
import CurlyMiniProject.CommonSpace.DTO.Default.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@ControllerAdvice
public class WorkExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity noItemErrorHandler(NoSuchElementException e) {
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER, null)
                , HttpStatus.NOT_FOUND);
    }
}
