package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.RequestStatus;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.repository.RequestAttachmentRepository;
import edu.sapientia.requestmanager.repository.RequestRepository;
import edu.sapientia.requestmanager.repository.entity.Attachment;
import edu.sapientia.requestmanager.repository.entity.Request;
import edu.sapientia.requestmanager.repository.entity.User;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class RequestService {

    private final OfficialRequestReferenceNumberService officialRequestReferenceNumberService;

    private final RequestRepository requestRepository;

    private final RequestAttachmentRepository attachmentRepository;

    private final RequestMapper requestMapper;

    public String approveRequest(String referenceNumber) {
        Request request = requestRepository.findByReferenceNumber(referenceNumber);
        request.setOfficialReferenceNumber(officialRequestReferenceNumberService.getNewOfficialReferenceNumber());
        request.setStatus(RequestStatus.APPROVED);
        return requestRepository.save(request).getOfficialReferenceNumber();
    }

    public String rejectRequest(String referenceNumber) {
        Request request = requestRepository.findByReferenceNumber(referenceNumber);
        request.setOfficialReferenceNumber(officialRequestReferenceNumberService.getNewOfficialReferenceNumber());
        request.setStatus(RequestStatus.REJECTED);
        return requestRepository.save(request).getOfficialReferenceNumber();
    }

    @SneakyThrows
    public String saveRequestInfo(long userId, RequestRequest res) {
        Request request = new Request();
        User user = new User();
        user.setId(userId);
        request.setUser(user);
        request.setName(res.getName());
        request.setJson(res.getJson());
        request.setDocument(res.getFile().getBytes());
        request.setDocumentType(res.getFile().getContentType());
        return requestRepository.save(request).getReferenceNumber();
    }

//    public void saveDocument(Long userId, String refNumber, String name, String type, byte[] bytes) {
//
//        if (Math.random() > 0.3) {
//            throw new RuntimeException("Test");
//        }
//        Request request = requestRepository.findByUserIdAndReferenceNumberOrOfficialReferenceNumber(userId, refNumber);
//        if (request.getAttachmentList() == null) {
//            request.setAttachmentList(new ArrayList<>());
//        }
//        Attachment document = new Attachment();
//        document.setReferenceNumber(refNumber);
//        document.setName(name);
//        document.setType(type);
//        document.setValue(bytes);
////        document.setUserId(request.getUser().getId());
////        document.setRequestId(request.getId());
//
//        request.getAttachmentList().add(document);
//        requestRepository.save(request);
//    }

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findAllByUserIdOrderByReferenceNumberDesc(id);
    }

    public Request getRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumber(userId, referenceNumber);
    }

    public Request findRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumberOrOfficialReferenceNumber(userId, referenceNumber);
    }

    public List<Attachment> getRequestAttachmentList(Long userId, String referenceNumber) {
        return new ArrayList<>();
//        return attachmentRepository.findByUserIdAndReferenceNumber(userId, referenceNumber);
    }

    public void requestUpload(String referenceNumber, String documentName) {
        // TODO hook in JMS
        Attachment attachment = new Attachment();
        attachment.setName(documentName);
        attachment.setRequestReferenceNumber(referenceNumber);
        attachmentRepository.save(attachment);
    }
}
