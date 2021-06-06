package edu.sapientia.requestmanager.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RequestAttachmentRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long requestId;

    private String referenceNumber;

    @OneToOne
    @JoinColumn(name = "attachment_type_id")
    private RequestAttachment requestAttachment;

}
