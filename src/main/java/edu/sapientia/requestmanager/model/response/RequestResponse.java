package edu.sapientia.requestmanager.model.response;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

//    private List<String> attachmentRequestList;

    private String documentType;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] document;

}
