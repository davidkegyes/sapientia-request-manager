package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAttachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String referenceNumber;

    private String name;

    private String type;

    private boolean requestUpload;

    @CreationTimestamp
    private LocalDateTime uploadDateTime;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] attachment;

}
