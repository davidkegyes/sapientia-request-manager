package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.RequestInfoResponse;
import edu.sapientia.requestmanager.model.response.RequestResponse;
import edu.sapientia.requestmanager.repository.entity.Request;
import edu.sapientia.requestmanager.repository.entity.RequestAttachmentRequest;
import org.aspectj.lang.annotation.After;
import org.mapstruct.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestMapper {

    @Mapping(target = "attachmentRequestList", ignore = true)
    RequestResponse mapToResponse(Request request);

    @AfterMapping
    default void mapAttachmentRequestList(Request request, @MappingTarget RequestResponse requestResponse) {
        if (CollectionUtils.isEmpty(request.getAttachmentRequestList())){
            requestResponse.setAttachmentRequestList(new ArrayList<>());
            return;
        }
        requestResponse.setAttachmentRequestList(request.getAttachmentRequestList().stream().map(RequestAttachmentRequest::getName).collect(Collectors.toList()));
    }

    @Mapping(target = "attachmentRequestList", ignore = true)
    RequestInfoResponse mapToRequestInfoResponse(Request request);

    @AfterMapping
    default void mapAttachmentRequestList(Request request, @MappingTarget RequestInfoResponse requestResponse) {
        if (CollectionUtils.isEmpty(request.getAttachmentRequestList())){
            requestResponse.setAttachmentRequestList(new ArrayList<>());
            return;
        }
        requestResponse.setAttachmentRequestList(request.getAttachmentRequestList().stream().map(RequestAttachmentRequest::getName).collect(Collectors.toList()));
    }

    List<RequestResponse> mapToResponseList(List<Request> requestList);

    List<RequestInfoResponse> mapToRequestInfoResponseList(List<Request> requestList);
}
