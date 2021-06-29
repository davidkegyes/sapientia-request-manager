package edu.sapientia.requestmanager.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestTemplateResponse {

    private Long id;

    private String uuid;

    private String name;

    private String description;

    private String language;

    private List<RequestTemplate.FormPart> form;

    private List<String> requiredDocuments;

}
