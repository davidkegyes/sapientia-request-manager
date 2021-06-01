package edu.sapientia.requestmanager.model.request;

import lombok.Data;

@Data
public class UploadRequiredRequest {

    private String referenceNumber;
    private String documentName;
}
