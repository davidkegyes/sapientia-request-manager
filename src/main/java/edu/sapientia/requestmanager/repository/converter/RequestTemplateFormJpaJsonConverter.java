package edu.sapientia.requestmanager.repository.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class RequestTemplateFormJpaJsonConverter implements AttributeConverter<ArrayList<RequestTemplate.FormPart>, String> {

    static ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ArrayList<RequestTemplate.FormPart> attribute) {
        return null;
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
