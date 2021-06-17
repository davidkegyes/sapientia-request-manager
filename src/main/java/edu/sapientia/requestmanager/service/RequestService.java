package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.RequestStatus;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.repository.RequestAttachmentRepository;
import edu.sapientia.requestmanager.repository.RequestRepository;
import edu.sapientia.requestmanager.repository.entity.Request;
import edu.sapientia.requestmanager.repository.entity.RequestAttachmentRequest;
import edu.sapientia.requestmanager.repository.entity.User;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Request> getRequestsByUserId(Long id) {
        return requestRepository.findAllByUserIdOrderByReferenceNumberDesc(id);
    }

    public Request getRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumber(userId, referenceNumber);
    }

    public Request findRequest(Long userId, String referenceNumber) {
        return requestRepository.findByUserIdAndReferenceNumberOrOfficialReferenceNumber(userId, referenceNumber);
    }

    public void requestAttachmentUpload(String referenceNumber, List<String> requestedAttachmentList) {
        Request request = requestRepository.findByReferenceNumber(referenceNumber);
        request.setAttachmentRequestList(requestedAttachmentList.stream().map(s -> new RequestAttachmentRequest(null, referenceNumber, s)).collect(Collectors.toList()));
        requestRepository.save(request);
    }
}
