package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.RequestResponse;
import edu.sapientia.requestmanager.repository.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestMapper {

    RequestResponse mapToResponse(Request request);

    List<RequestResponse> mapToResponseList(List<Request> requestList);
}
