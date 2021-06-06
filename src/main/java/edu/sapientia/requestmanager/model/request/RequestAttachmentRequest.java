package edu.sapientia.requestmanager.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestAttachmentRequest {

    private String requestReferenceNumber;
    private String name;
    private MultipartFile file;

}
