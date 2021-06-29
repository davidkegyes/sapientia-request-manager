package edu.sapientia.requestmanager.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestTemplateRequest {

    private String uuid;
    private String name;
    private String description;
    private String language;
    private List<RequestFormPart> form;
    private List<String> requiredDocuments;
}
