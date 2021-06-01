package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Id
    @GeneratedValue(generator = "requestRegistryNumberGenerator")
    @GenericGenerator(name = "requestRegistryNumberGenerator", strategy = "edu.sapientia.requestmanager.generator.RequestRegistryNumberGenerator")
    private String referenceNumber;

    private String name;

    private String json;

    private String documentType;

    private String status;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] document;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
