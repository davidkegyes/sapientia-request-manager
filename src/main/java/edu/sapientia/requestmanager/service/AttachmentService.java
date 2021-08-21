package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.model.RequestStatus;
import edu.sapientia.requestmanager.repository.AttachmentRepository;
import edu.sapientia.requestmanager.repository.entity.Attachment;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Data
@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final RequestService requestService;

    @Transactional
    public void attachDocumentToRequest(String requestReferenceNumber, String documentName, String documentType, byte[] document) {
        Attachment attachment = new Attachment();
        attachment.setValue(document);
        attachment.setName(documentName);
        attachment.setType(documentType);
        attachment.setRequestReferenceNumber(requestReferenceNumber);
        attachmentRepository.save(attachment);

        if (attachmentRepository.countAllByRequestReferenceNumber(requestReferenceNumber) == requestService.getRequestRequiredDocumentCount(requestReferenceNumber)) {
            requestService.updateRequestStatus(requestReferenceNumber, RequestStatus.NEW);
        }
    }

    public List<Attachment> getAttachmentListForReference(String requestReferenceNumber) {
        return attachmentRepository.getAttachmentByRequestReferenceNumber(requestReferenceNumber);
    }

    public void deleteAttachment(String uuid) {
        attachmentRepository.deleteByUuid(uuid);

    }
}
