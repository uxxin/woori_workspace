package dev.rest.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;

@Builder
@Getter
// 에러 응답용 DTO
public class ErrorResponse {

    // 에러 코드
    private String code;

    // 에러 메시지
    private String message;

    // 각 필드별 유효성 검증 에러
    private List<FieldError> errors;

    @Getter
    @Builder
    public static class FieldError {
        private String field;
        private Object value;
        private String reason;
    }

    public static ErrorResponse fromBindingResult(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors().stream()
                .map(error -> FieldError.builder()
                        .field(error.getField())
                        .value(error.getRejectedValue())
                        .reason(error.getDefaultMessage())
                        .build())
                .toList();

        return ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .errors(fieldErrors)
                .build();
    }
}
