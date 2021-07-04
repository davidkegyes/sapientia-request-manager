package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.sapientia.requestmanager.model.RequestStatus;
import edu.sapientia.requestmanager.repository.converter.StringListJpaJsonConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity(name = "Request")
@Table(name = "request")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Request implements Serializable {

    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "inspector_user_id")
    private User inspectorUser;

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
    @Column(nullable = false, name = "create_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(nullable = false, name = "update_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDateTime;

    private String documentType;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] document;

    private String form;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Convert(converter = StringListJpaJsonConverter.class)
    private List<String> requiredDocuments;

}
