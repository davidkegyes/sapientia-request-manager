package edu.sapientia.requestmanager.repository.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class RequestTemplateDocumentJpaJsonConverter implements AttributeConverter<ArrayList<RequestTemplate.Document>, String> {

    static ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(ArrayList<RequestTemplate.Document> attribute) {
        return mapper.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public ArrayList<RequestTemplate.Document> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.readValue(dbData, mapper.getTypeFactory().constructCollectionType(List.class, RequestTemplate.Document.class));
    }
}
