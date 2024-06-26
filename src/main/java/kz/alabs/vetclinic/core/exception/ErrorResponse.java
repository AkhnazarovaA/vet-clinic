package kz.alabs.vetclinic.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final int status;
    private final Map<String, String> messages;
    private String stackTrace;
    private List<ValidationError> errors;

    private record ValidationError(String field, String message) {
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }

}
