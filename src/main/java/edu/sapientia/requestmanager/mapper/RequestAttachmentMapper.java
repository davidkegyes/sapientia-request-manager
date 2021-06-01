package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.RequestAttachmentResponse;
import edu.sapientia.requestmanager.repository.entity.RequestAttachment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestAttachmentMapper {

    RequestAttachmentResponse mapToResponse(RequestAttachment requestAttachment);

    List<RequestAttachmentResponse> mapToResponse(List<RequestAttachment> requestAttachment);
}
