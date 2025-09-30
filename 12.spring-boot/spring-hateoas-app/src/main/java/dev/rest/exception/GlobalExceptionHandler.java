package dev.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // MethodArgumentNotValidException이 발생하면 handleValidation()이 동작
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        // MethodArgumentNotValidException예외가 발생했을 때 수행할 로직 작성 부분
        // 1. ErrorResponse에 작성된 fromBindingResult() 호출해서 ErrorResponse 객체 생성
        ErrorResponse errorResponse = ErrorResponse.fromBindingResult(ex.getBindingResult());

        // 2. 클라이언트에게 응답
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // TODO: ProductNotFound 예외가 발생하면,
    // 해당 예외에 관련된 메시지가 클라이언트에게 응답되도록
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound() {
        ErrorResponse errorResponse = ErrorResponse.builder().code(ApiResponseCode.DATA_NOT_FOUND.getCode())
                .message(ApiResponseCode.DATA_NOT_FOUND.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
