package edu.sapientia.requestmanager.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Builder
@Data
public class ErrorResponse implements Serializable {

    private HttpStatus httpStatus;
    private String message;
    private Exception exception;
}
