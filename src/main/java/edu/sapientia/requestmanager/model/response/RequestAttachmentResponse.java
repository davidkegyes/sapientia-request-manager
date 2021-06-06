package edu.sapientia.requestmanager.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAttachmentResponse {

    private Long id;

    private String name;

    private List<String> accept;
}
