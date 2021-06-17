package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.sapientia.requestmanager.model.RequestStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Request")
@Table(name = "request")
//@IdClass(RequestId.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Request implements Serializable {

//    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "reference_number")
    private String referenceNumber;

    private String officialReferenceNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status = RequestStatus.NEW;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private String documentType;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] document;

    private String json;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "referenceNumber")
    private List<RequestAttachmentRequest> attachmentRequestList;
}
