package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.repository.RequestAttachmentRepository;
import edu.sapientia.requestmanager.repository.RequestRepository;
import edu.sapientia.requestmanager.repository.entity.Request;
import edu.sapientia.requestmanager.repository.entity.RequestAttachment;
import edu.sapientia.requestmanager.repository.entity.User;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class RequestService {

    private final RequestRepository requestRepository;
    
    private final RequestAttachmentRepository attachmentRepository;

    private final RequestMapper requestMapper;

    @SneakyThrows
    public String saveRequestInfo(long userId, RequestRequest res){
        Request request = new Request();
        User user = new User();
        user.setId(userId);
        request.setUser(user);
        request.setName(res.getName());
        request.setReferenceNumber(res.getReferenceNumber());
        request.setJson(res.getJson());
        request.setDocument(res.getFile().getBytes());
        request.setDocumentType(res.getFile().getContentType());
        return requestRepository.save(request).getReferenceNumber();
    }

    public void saveDocument(Long userId, String refNumber, String name, String type, byte[] bytes) {
        RequestAttachment document = new RequestAttachment();
        document.setReferenceNumber(refNumber);
        document.setName(name);
        document.setType(type);
        document.setAttachment(bytes);
        document.setUserId(userId);
        attachmentRepository.save(document);
    }

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findAllByUserIdOrderByReferenceNumberDesc(id);
    }

    public Request getRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumber(userId, referenceNumber);
    }

    public List<RequestAttachment> getRequestAttachmentList(Long userId, String referenceNumber){
        return attachmentRepository.findByUserIdAndReferenceNumber(userId, referenceNumber);
    }

    public void requestUpload(String referenceNumber, String documentName){
        // TODO hook in JMS
        RequestAttachment requestAttachment = new RequestAttachment();
        requestAttachment.setRequestUpload(true);
        requestAttachment.setName(documentName);
        requestAttachment.setReferenceNumber(referenceNumber);
        attachmentRepository.save(requestAttachment);
    }
}
