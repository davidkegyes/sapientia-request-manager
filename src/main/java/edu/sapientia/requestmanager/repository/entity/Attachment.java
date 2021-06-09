package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attachment")
@IdClass(AttachmentId.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Attachment implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid")
    private String uuid;

    private String requestReferenceNumber;

    private String name;

    private String type;

    @CreationTimestamp
    private LocalDateTime uploadDateTime;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] value;

}
