package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.sapientia.requestmanager.repository.converter.RequestTemplateFormJpaJsonConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String language;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "request_template_attachment_name",
            joinColumns = {@JoinColumn(name = "request_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "attachment_name_id")}
    )
    private List<RequestAttachment> attachmentList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Convert(converter = RequestTemplateFormJpaJsonConverter.class)
    private List<FormPart> form;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FormPart {
        private String wrapper;
        private String style;
        private String type;
        private String text;
        private String dateText;
        private String signatureText;
        private String name;
        private String variable;
        private List<FormPartVariable> variables;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FormPartVariable {
        private String name;
        private String type;
        private String placeholder;
        private String error;
        private String value;
    }
}
