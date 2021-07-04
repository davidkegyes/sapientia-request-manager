package edu.sapientia.requestmanager.model.response;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestResponse {

    private String referenceNumber;

    private String officialReferenceNumber;

    private String name;

    private String status;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private String documentType;

    private UserInfoResponse user;

    private UserInfoResponse inspectorUser;

    private List<String> requiredDocuments;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] document;

}
