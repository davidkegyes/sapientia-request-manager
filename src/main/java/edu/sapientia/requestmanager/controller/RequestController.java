package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.model.response.RequestInfoResponse;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import edu.sapientia.requestmanager.service.RequestService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@CrossOrigin
public class RequestController {

    private final RequestService requestService;

    private final RequestMapper requestMapper;

    @PostMapping(value = "/request/approve/{referenceNumber}")
    public ResponseEntity approveRequest(@PathVariable String referenceNumber) {
        return ResponseEntity.ok(requestService.approveRequest(referenceNumber));
    }

    @PostMapping(value = "/request/reject/{referenceNumber}")
    public ResponseEntity rejectRequest(@PathVariable String referenceNumber) {
        return ResponseEntity.ok(requestService.rejectRequest(referenceNumber));
    }

    @PostMapping(value = "/request/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity saveRequest(@ModelAttribute RequestRequest request, Authentication authentication) {
        return ResponseEntity.ok(requestService.saveRequestInfo(((AuthorizedUser) authentication.getPrincipal()).getId(), request));
    }

    @GetMapping("/request/{referenceNumber}")
    public ResponseEntity getRequestByReferenceNumber(@PathVariable String referenceNumber, Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToResponse(requestService.findRequest(((AuthorizedUser) authentication.getPrincipal()).getId(), referenceNumber)));
    }

    @GetMapping("/request/info/{referenceNumber}")
    public ResponseEntity<RequestInfoResponse> getRequestInfoByReferenceNumber(@PathVariable String referenceNumber, Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponse(requestService.findRequest(((AuthorizedUser) authentication.getPrincipal()).getId(), referenceNumber)));
    }
//
//    @GetMapping("/request/document/{referenceNumber}")
//    public ResponseEntity getRequestDocumentByReferenceNumber(@PathVariable String referenceNumber, Authentication authentication) {
//        return ResponseEntity.ok(requestMapper.mapToResponse(requestService.findRequest(((AuthorizedUser) authentication.getPrincipal()).getId(), referenceNumber)));
//    }

    @GetMapping("/request/list")
    public ResponseEntity<List<RequestInfoResponse>> getMyRequests(Authentication authentication) {
        return ResponseEntity.ok(requestMapper.mapToRequestInfoResponseList(requestService.getRequestsByUserId(((AuthorizedUser) authentication.getPrincipal()).getId())));
    }

}