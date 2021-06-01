package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.request.RequestAttachmentRequest;
import edu.sapientia.requestmanager.model.request.RequestRequest;
import edu.sapientia.requestmanager.model.request.UploadRequiredRequest;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import edu.sapientia.requestmanager.service.RequestService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
public class RequestController {

    private final RequestService requestService;

    private final RequestMapper requestMapper;

    @PostMapping(value = "/request/attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadRequestDocument(@ModelAttribute RequestAttachmentRequest request, Authentication authentication) throws IOException {
        requestService.saveDocument(((AuthorizedUser)authentication.getPrincipal()).getId(), request.getReferenceNumber(), request.getName(), request.getFile().getContentType(), request.getFile().getBytes());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/request/attachments/{referenceNumber}")
    public ResponseEntity getAttachmentList(@PathVariable String referenceNumber, Authentication authentication){
        return ResponseEntity.ok(requestService.getRequestAttachmentList(((AuthorizedUser)authentication.getPrincipal()).getId(), referenceNumber));
    }

    @PostMapping(value = "/request/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity saveRequest(@ModelAttribute RequestRequest request, Authentication authentication) {
        return ResponseEntity.ok(requestService.saveRequestInfo(((AuthorizedUser)authentication.getPrincipal()).getId(),request));
    }

    @GetMapping("/request/{referenceNumber}")
    public ResponseEntity getRequestByReferenceNumber(@PathVariable String referenceNumber, Authentication authentication){
        return ResponseEntity.ok(requestService.getRequest(((AuthorizedUser)authentication.getPrincipal()).getId(), referenceNumber));
    }

    @GetMapping("/request/list")
    @CrossOrigin
    public ResponseEntity getMyRequests(Authentication authentication){
        return ResponseEntity.ok(requestMapper.mapToResponseList(requestService.getRequestsByUserId(((AuthorizedUser)authentication.getPrincipal()).getId())));
    }

    @PostMapping("/request/uploadRequired")
    @CrossOrigin
    public ResponseEntity requestFileUpload(@RequestBody UploadRequiredRequest request){
        requestService.requestUpload(request.getReferenceNumber(), request.getDocumentName());
        return ResponseEntity.ok().build();
    }
}