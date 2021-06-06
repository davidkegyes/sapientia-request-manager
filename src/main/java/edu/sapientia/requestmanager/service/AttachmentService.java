package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.entity.Attachment;
import edu.sapientia.requestmanager.repository.entity.AttachmentRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    public void attachDocumentToRequest(String requestReferenceNumber, String documentName, String documentType, byte[] document){
        Attachment attachment = new Attachment();
        attachment.setValue(document);
        attachment.setName(documentName);
        attachment.setType(documentType);
        attachment.setRequestReferenceNumber(requestReferenceNumber);
        attachmentRepository.save(attachment);
    }

    public List<Attachment> getAttachmentListForReference(String requestReferenceNumber){
        return attachmentRepository.getAttachmentByRequestReferenceNumber(requestReferenceNumber);
    }
}
