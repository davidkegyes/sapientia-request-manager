package edu.sapientia.requestmanager.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestFormPart {

    private String id;
    private String wrapper;
    private String style;
    private String type;
    private String name;
    private String text;
    private String dateText;
    private String signatureText;
    private String hint;
    private String component;
    private List<RequestFormPartVariable> variables;
}
