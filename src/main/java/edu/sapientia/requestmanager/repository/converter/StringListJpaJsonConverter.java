package edu.sapientia.requestmanager.repository.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class StringListJpaJsonConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return MAPPER.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        return MAPPER.readValue(dbData, MAPPER.getTypeFactory().constructCollectionType(List.class, String.class));
    }
}
