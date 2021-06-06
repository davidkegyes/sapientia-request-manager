package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.model.response.ErrorResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({SizeLimitExceededException.class, FileSizeLimitExceededException.class})
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(SizeException e, WebRequest request) {
        return handleExceptionInternal(e,
                ErrorResponse.builder()
                        .message("File exceeds the limit of " + DataSize.ofBytes(e.getPermittedSize()).toKilobytes() + " KB Current file size is: " + DataSize.ofBytes(e.getActualSize()).toKilobytes() + " KB")
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .exception(e)
                        .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
