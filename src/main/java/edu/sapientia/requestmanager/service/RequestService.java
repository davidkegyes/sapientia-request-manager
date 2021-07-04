package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.RequestStatus;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.repository.RequestAttachmentRepository;
import edu.sapientia.requestmanager.repository.RequestRepository;
import edu.sapientia.requestmanager.repository.UserRepository;
import edu.sapientia.requestmanager.repository.entity.Request;
import edu.sapientia.requestmanager.repository.entity.User;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class RequestService {

    private final OfficialRequestReferenceNumberService officialRequestReferenceNumberService;

    private final RequestRepository requestRepository;

    private final RequestAttachmentRepository attachmentRepository;

    private final RequestMapper requestMapper;

    private final UserRepository userRepository;

    public String approveRequest(String referenceNumber, Long inspectorUserId) {
        return updateRequestStatus(referenceNumber, inspectorUserId, RequestStatus.APPROVED);
    }

    public String rejectRequest(String referenceNumber, Long inspectorUserId) {
        return updateRequestStatus(referenceNumber, inspectorUserId, RequestStatus.REJECTED);
    }

    public String updateRequestStatus(String referenceNumber, Long inspectorUserId, RequestStatus requestStatus) {
        Request request = requestRepository.findByReferenceNumber(referenceNumber);
        request.setOfficialReferenceNumber(officialRequestReferenceNumberService.getNewOfficialReferenceNumber());
        request.setInspectorUser(userRepository.findById(inspectorUserId).get());
        request.setStatus(requestStatus);
        return requestRepository.save(request).getOfficialReferenceNumber();
    }

    @SneakyThrows
    public String saveRequestInfo(long userId, RequestRequest res) {
        Request request = new Request();
        User user = new User();
        user.setId(userId);
        request.setUser(user);
        request.setName(res.getName());
        request.setForm(res.getJson());
        request.setDocument(res.getFile().getBytes());
        request.setDocumentType(res.getFile().getContentType());
        request.setRequiredDocuments(res.getRequiredDocuments());
        return requestRepository.save(request).getReferenceNumber();
    }

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findAllByUserIdOrderByCreateDateTimeDesc(id);
    }

    public Request getRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumber(userId, referenceNumber);
    }

    public Request findRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumberOrOfficialReferenceNumber(userId, referenceNumber);
    }

}
