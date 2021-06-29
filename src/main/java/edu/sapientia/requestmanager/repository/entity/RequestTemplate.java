package edu.sapientia.requestmanager.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.sapientia.requestmanager.repository.converter.RequestTemplateFormJpaJsonConverter;
import edu.sapientia.requestmanager.repository.converter.StringListJpaJsonConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestTemplate implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid")
    private String uuid;

    private String name;
    private String description;
    private String language;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Convert(converter = RequestTemplateFormJpaJsonConverter.class)
    private List<FormPart> form;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Convert(converter = StringListJpaJsonConverter.class)
    private List<String> requiredDocuments;

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
        private String hint;
        private List<FormPartVariable> variables;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FormPartVariable {
        private String name;
        private String type;
        private String placeholder;
        private String hint;
        private String error;
        private String value;
    }
}
