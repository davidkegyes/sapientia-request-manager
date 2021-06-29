package edu.sapientia.requestmanager.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestFormPartVariable {

    private String id;
    private String name;
    private String type;
    private String hint;
    private Integer min;
    private Integer max;
}
