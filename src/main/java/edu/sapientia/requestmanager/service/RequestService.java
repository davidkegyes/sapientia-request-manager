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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data @Slf4j
@Service
public class RequestService {

    private final OfficialRequestReferenceNumberService officialRequestReferenceNumberService;

    private final RequestRepository requestRepository;

    private final RequestAttachmentRepository attachmentRepository;

    private final RequestMapper requestMapper;

    private final UserRepository userRepository;

    private final MailService mailService;

    @Value("${ui.host}")
    private String uiHost;

    public Request approveRequest(String referenceNumber, Long inspectorUserId) {
        return updateRequestStatus(referenceNumber, inspectorUserId, RequestStatus.APPROVED);
    }

    public Request rejectRequest(String referenceNumber, Long inspectorUserId) {
        return updateRequestStatus(referenceNumber, inspectorUserId, RequestStatus.REJECTED);
    }

    public Request updateRequestStatus(String referenceNumber, Long inspectorUserId, RequestStatus requestStatus) {
        Request request = requestRepository.findByReferenceNumber(referenceNumber);
        request.setOfficialReferenceNumber(officialRequestReferenceNumberService.getNewOfficialReferenceNumber());
        request.setInspectorUser(userRepository.findById(inspectorUserId).get());
        request.setStatus(requestStatus);
        String s = RequestStatus.APPROVED.equals(requestStatus) ? "ELFOGADVA" : "ELUTASÍTVA";
        try {
            StringBuilder body = new StringBuilder()
                    .append("Kérése statusza módosult ").append(s)
                    .append("\n\r")
                    .append("\n\r")
                    .append("Kérését fölülvizsgálta : ").append(request.getInspectorUser().getFirstname()).append(" ").append(request.getInspectorUser().getLastname())
                    .append("\n\r")
                    .append("\n\r")
                    .append("Kérését megtekintheti itt: ").append(uiHost).append("/inspect/").append(request.getReferenceNumber());
            mailService.sendMail(request.getUser().getEmail(), "Kérése statusza módosult " + s, body.toString());
        } catch (Exception e) {
            log.error("Error during email approval email notification", e);
        }

        return requestRepository.save(request);
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
        if (!CollectionUtils.isEmpty(request.getRequiredDocuments())){
            request.setStatus(RequestStatus.INCOMPLETE);
        } else {
            request.setStatus(RequestStatus.NEW);
        }
        return requestRepository.save(request).getReferenceNumber();
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
