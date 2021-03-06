package live.anvill.ws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> appException(UserServiceException ex, WebRequest webRequest){
        ErrorLog errorLog = new ErrorLog(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorLog,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> appException(Exception ex, WebRequest webRequest){
        ErrorLog errorLog = new ErrorLog(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorLog,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
