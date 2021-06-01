package edu.sapientia.requestmanager.model.response;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Data
public class RequestAttachmentResponse {

    private Long id;

    private String referenceNumber;

    private String name;

    private String type;

    @CreationTimestamp
    private LocalDateTime uploadDateTime;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] attachment;

}
