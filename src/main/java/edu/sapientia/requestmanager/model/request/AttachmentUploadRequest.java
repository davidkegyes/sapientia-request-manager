package edu.sapientia.requestmanager.model.request;

import lombok.Data;

import java.util.List;

@Data
public class AttachmentUploadRequest {

    private String referenceNumber;
    private List<String> requestedAttachments;
}
