package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.RequestAttachmentResponse;
import edu.sapientia.requestmanager.repository.entity.AcceptedDocumentFormat;
import edu.sapientia.requestmanager.repository.entity.RequestAttachment;
import org.mapstruct.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestAttachmentMapper {

    @Mapping(target = "accept", ignore = true)
    RequestAttachmentResponse map(RequestAttachment requestAttachment);

    @AfterMapping
    default void mapAcceptedFormatsToString(RequestAttachment requestAttachment, @MappingTarget RequestAttachmentResponse requestAttachmentResponse) {
        if (CollectionUtils.isEmpty(requestAttachment.getFormatList())) {
            return;
        }
        requestAttachmentResponse.setAccept(requestAttachment.getFormatList().stream().map(AcceptedDocumentFormat::getFormat).collect(Collectors.toList()));
    }

    List<RequestAttachmentResponse> map(List<RequestAttachment> requestAttachment);
}
