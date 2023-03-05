package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//restcontrolleradvice의 대상을 지정하지 않으면 모든 컨트롤러 다(글로번)
@RestControllerAdvice("hello.exception")
@Slf4j
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ErrorResult illiagelExHandler(IllegalArgumentException e){
        log.error("[exception handler] ex",e);
        return new ErrorResult("BAD",e.getMessage());
    }

    @ExceptionHandler // 아래 아규먼트랑 같은 경우 생략 가능
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler]ex",e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){ // 해결할 핸들러가 없을 경우에 여기에서 해결한다.
        log.error("[exceptionHandler]ex",e);
        return new ErrorResult("ex","내부오류");
    }
}
