package edu.sapientia.requestmanager.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestRequest {

    private Integer templateId;
    private String referenceNumber;
    private String name;
    private String json;
    private MultipartFile file;

}
