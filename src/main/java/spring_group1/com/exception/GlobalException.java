package spring_group1.com.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice

public class GlobalException {


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleParameterValidation(HandlerMethodValidationException ex) {

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Invalid Parameter");
        detail.setDetail("Parameter validation failed (e.g. id must be > 0)");
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }



    @ExceptionHandler(DuplicateEmailException.class)
    public ProblemDetail handleDuplicateEmail(DuplicateEmailException ex) {

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        detail.setTitle("Duplicate Email");
        detail.setDetail(ex.getMessage());
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }



    @ExceptionHandler(DuplicateName.class)
    public ProblemDetail handleDuplicateName(DuplicateName ex) {

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        detail.setTitle("Duplicate Name");
        detail.setDetail(ex.getMessage());
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }


    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ProblemDetail handleNotFound(NotFoundExceptionHandler ex) {

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detail.setTitle("ID not Found");
        detail.setDetail(ex.getMessage());
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }



    @ExceptionHandler(GreaterException.class)
    public ProblemDetail greaterThan(GreaterException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setProperty("timestamp" , Instant.now());
        return  problemDetail;
    }

    @ExceptionHandler(EmailNotFound.class)
    public ProblemDetail handleNotFound(EmailNotFound ex) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detail.setTitle("Email not found");
        detail.setDetail(ex.getMessage());
        detail.setProperty("timestamp", Instant.now());
        return detail;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validationHandler(MethodArgumentNotValidException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Failed");

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("timestamp" , Instant.now());

        return problemDetail;
    }




}
