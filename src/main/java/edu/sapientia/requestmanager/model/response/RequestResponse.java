package edu.sapientia.requestmanager.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestResponse {

    private Long id;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private String referenceNumber;

    private String name;

    private String documentType;

    private String status;

    private byte[] document;

}
