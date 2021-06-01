package edu.sapientia.requestmanager.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestAttachmentRequest {

    private String referenceNumber;
    private String name;
    private MultipartFile file;

}
