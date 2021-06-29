package edu.sapientia.requestmanager.repository.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class RequestTemplateFormJpaJsonConverter implements AttributeConverter<List<RequestTemplate.FormPart>, String> {

    static ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<RequestTemplate.FormPart> attribute) {
        return mapper.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public ArrayList<RequestTemplate.FormPart> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.readValue(dbData, mapper.getTypeFactory().constructCollectionType(List.class, RequestTemplate.FormPart.class));
    }
}
