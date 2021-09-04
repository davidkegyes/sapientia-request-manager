package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.RequestStatus;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.repository.RequestAttachmentRepository;
import edu.sapientia.requestmanager.repository.RequestRepository;
import edu.sapientia.requestmanager.repository.UserRepository;
import edu.sapientia.requestmanager.repository.entity.Request;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
@Slf4j
@Service
public class RequestService {

    private final OfficialRequestReferenceNumberService officialRequestReferenceNumberService;

    private final RequestRepository requestRepository;

    private final RequestAttachmentRepository attachmentRepository;

    private final RequestMapper requestMapper;

    private final UserRepository userRepository;

    private final NotificationService notificationService;

    public int getRequestRequiredDocumentCount(String referenceNumber) {
        return requestRepository.findByReferenceNumber(referenceNumber).getRequiredDocuments().size();
    }

    public Request approveRequest(String referenceNumber, Long inspectorUserId) {
        return updateRequestStatus(referenceNumber, inspectorUserId, RequestStatus.APPROVED);
    }

    public Request rejectRequest(String referenceNumber, Long inspectorUserId) {
        return updateRequestStatus(referenceNumber, inspectorUserId, RequestStatus.REJECTED);
    }

    public Request updateRequestStatus(String referenceNUmber, RequestStatus status) {
        return updateRequestStatus(referenceNUmber, null, status);
    }

    public Request updateRequestStatus(String referenceNumber, Long inspectorUserId, RequestStatus requestStatus) {
        Request request = requestRepository.findByReferenceNumber(referenceNumber);
        request.setInspectorUser(inspectorUserId != null ? userRepository.findById(inspectorUserId).get() : null);
        if ((!RequestStatus.REJECTED.equals(request.getStatus()) && !RequestStatus.APPROVED.equals(request.getStatus()))
                && (RequestStatus.REJECTED.equals(requestStatus) || RequestStatus.APPROVED.equals(requestStatus))) {
            request.setOfficialReferenceNumber(officialRequestReferenceNumberService.getNewOfficialReferenceNumber());
        }
        request.setStatus(requestStatus);
        try {
            request = requestRepository.save(request);
            Request finalRequest = request;
            notificationService.notifyOwnerRequestStatusChanged(finalRequest);
        } catch (Exception e) {
            log.error("Error during request status update", request.getReferenceNumber(), e);
        }
        return request;
    }

    @SneakyThrows
    public String saveRequest(long userId, RequestRequest res) {
        Request request = new Request();
        request.setUser(userRepository.findById(userId).get());
        request.setName(res.getName());
        request.setForm(res.getJson());
        request.setDocument(res.getFile().getBytes());
        request.setDocumentType(res.getFile().getContentType());
        request.setRequiredDocuments(res.getRequiredDocuments());
        if (!CollectionUtils.isEmpty(request.getRequiredDocuments())) {
            request.setStatus(RequestStatus.INCOMPLETE);
        } else {
            request.setStatus(RequestStatus.NEW);
            notificationService.notifySecretaryOfNewRequest(request);
        }
        request = requestRepository.save(request);
        notificationService.notifyOwnerRequestStatusChanged(request);
        return request.getReferenceNumber();
    }

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findAllByUserIdOrderByCreateDateTimeDesc(id);
    }

    public List<Request> getAllEligibleRequest() {
        return requestRepository.findByStatusNotOrderByCreateDateTimeDesc(RequestStatus.INCOMPLETE);
    }

    public Request findRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumberOrOfficialReferenceNumber(userId, referenceNumber);
    }

    public Request findRequest(String referenceNumber) {
        return requestRepository.findByReferenceNumber(referenceNumber);
    }

}
