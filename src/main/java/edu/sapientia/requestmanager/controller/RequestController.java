package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.model.response.RequestInfoResponse;
import edu.sapientia.requestmanager.model.response.RequestResponse;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import edu.sapientia.requestmanager.service.RequestService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@CrossOrigin
@Slf4j
public class RequestController {

    private final RequestService requestService;

    private final RequestMapper requestMapper;

    @PostMapping(value = "/request/approve/{referenceNumber}")
    @PreAuthorize("hasAuthority('APPROVE_APPLICATION')")
    public ResponseEntity approveRequest(@PathVariable String referenceNumber, Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponse(requestService.approveRequest(referenceNumber, ((AuthorizedUser) authentication.getPrincipal()).getId())));
    }

    @PostMapping(value = "/request/reject/{referenceNumber}")
    @PreAuthorize("hasAuthority('REJECT_APPLICATION')")
    public ResponseEntity rejectRequest(@PathVariable String referenceNumber, Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponse(requestService.rejectRequest(referenceNumber, ((AuthorizedUser) authentication.getPrincipal()).getId())));
    }

    @PostMapping(value = "/request/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('UPLOAD_APPLICATION')")
    public ResponseEntity saveRequest(@ModelAttribute RequestRequest request, Authentication authentication) {
        return ResponseEntity.ok(requestService.saveRequest(((AuthorizedUser) authentication.getPrincipal()).getId(), request));
    }

    @GetMapping("/request/{referenceNumber}")
    @PreAuthorize("hasAuthority('VIEW_APPLICATION') or hasAuthority('VIEW_ALL_APPLICATIONS')")
    @PostAuthorize("#authentication.principal.id == returnObject.body.user.id or hasAuthority('VIEW_ALL_APPLICATIONS')")
    public ResponseEntity<RequestResponse> getRequestByReferenceNumber(@PathVariable String referenceNumber, Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToResponse(requestService.findRequest(referenceNumber)));
    }

    @GetMapping("/request/info/{referenceNumber}")
    @PreAuthorize("hasAuthority('VIEW_APPLICATION')")
    public ResponseEntity<RequestInfoResponse> getRequestInfoByReferenceNumber(@PathVariable String referenceNumber, Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponse(requestService.findRequest(((AuthorizedUser) authentication.getPrincipal()).getId(), referenceNumber)));
    }

    @GetMapping("/request/list")
    @PreAuthorize("hasAuthority('VIEW_APPLICATION')")
    public ResponseEntity<List<RequestInfoResponse>> getMyRequests(Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponseList(requestService.getRequestsByUserId(((AuthorizedUser) authentication.getPrincipal()).getId())));
    }

    @GetMapping("/request/listAll")
    @PreAuthorize("hasAuthority('VIEW_ALL_APPLICATIONS')")
    public ResponseEntity<List<RequestInfoResponse>> getAllRequests(Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponseList(requestService.getAllEligibleRequest()));
    }

}