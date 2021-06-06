package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.AttachmentResponse;
import edu.sapientia.requestmanager.repository.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AttachmentMapper {

    AttachmentResponse mapToResponse(Attachment attachment);

    List<AttachmentResponse> mapToResponse(List<Attachment> attachment);
}
