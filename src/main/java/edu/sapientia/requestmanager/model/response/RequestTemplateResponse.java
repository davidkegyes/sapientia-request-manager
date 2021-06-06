package edu.sapientia.requestmanager.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestTemplateResponse {

    private Long id;

    private String name;

    private String description;

    private String language;

    private List<RequestAttachmentResponse> attachmentList;

    private List<RequestTemplate.FormPart> form;

}
