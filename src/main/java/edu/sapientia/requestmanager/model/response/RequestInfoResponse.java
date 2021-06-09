package edu.sapientia.requestmanager.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestInfoResponse {

    private String referenceNumber;

    private String officialReferenceNumber;

    private String name;

    private String status;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

}
