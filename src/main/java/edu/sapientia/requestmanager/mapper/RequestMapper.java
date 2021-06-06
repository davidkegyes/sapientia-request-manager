package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.RequestResponse;
import edu.sapientia.requestmanager.repository.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestMapper {

    @Mapping(target = "official", expression = "java(request.getOfficialReferenceNumber()!= null && !request.getReferenceNumber().isEmpty())")
    RequestResponse mapToResponse(Request request);

    List<RequestResponse> mapToResponseList(List<Request> requestList);
}
