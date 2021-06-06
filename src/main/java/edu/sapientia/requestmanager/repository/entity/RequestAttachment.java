package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "attachment_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "attachment_type_format",
            joinColumns = {@JoinColumn(name = "name_id")},
            inverseJoinColumns = {@JoinColumn(name = "accept_type_id")}
    )
    private List<AcceptedDocumentFormat> formatList;
}
