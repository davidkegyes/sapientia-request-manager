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
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private Long userId;

//    private Long requestId;

    private String requestReferenceNumber;

    private String name;

    private String type;

    @CreationTimestamp
    private LocalDateTime uploadDateTime;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] value;

}
