package edu.sapientia.requestmanager.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class RequestRequest {

    private String templateId;
    private String name;
    private String json;
    private List<String> requiredDocuments;
    private MultipartFile file;

}
