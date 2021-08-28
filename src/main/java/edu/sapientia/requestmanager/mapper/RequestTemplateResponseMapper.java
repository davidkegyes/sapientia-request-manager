package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.RequestTemplateResponse;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestTemplateResponseMapper {

    RequestTemplateResponse map(RequestTemplate requestTemplate);

    List<RequestTemplateResponse> map(List<RequestTemplate> requestTemplate);
}
