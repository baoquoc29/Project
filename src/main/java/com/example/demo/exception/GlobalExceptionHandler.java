package com.example.demo.exception;

import com.example.demo.formatresponse.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // xu ly ngoai ve ve App
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ApiResponse response = new ApiResponse();
        ErrorCode errorCode = e.getErrorCode();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // xu ly ngoai le run time
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    // xu ly ngoai le ve null
    @ExceptionHandler(value = NullPointerException.class)
    ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.NO_CONTENT.value());
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // xu ly ngoai le ve rang buoc khoa
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // xu ly ngoai le ve kieu du lieu
    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        apiResponse.setMessage("This method is not supported for this request.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Validation error: " + exception.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    // Handle access denial (for Spring Security)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.FORBIDDEN.value());
        apiResponse.setMessage("Access denied: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
    }

    // Handle unreadable request body (JSON/XML errors)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Malformed JSON request: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    // Handle constraint violations (e.g., Hibernate Validator)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Constraint violation: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }



}
