package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.request.RequestTemplateRequest;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestTemplateRequestMapper {

    RequestTemplate mapToEntity(RequestTemplateRequest requestTemplateRequest);
}
