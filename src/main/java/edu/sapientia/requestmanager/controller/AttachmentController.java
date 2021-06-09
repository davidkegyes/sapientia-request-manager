package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.model.request.RequestAttachmentRequest;
import edu.sapientia.requestmanager.service.AttachmentService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Data
@CrossOrigin
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    // TODO PreAuthorize/Validate if the user owns the request with the referencedNumber and exists
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadRequestDocument(@ModelAttribute RequestAttachmentRequest request) throws IOException {
        attachmentService.attachDocumentToRequest(request.getRequestReferenceNumber(), request.getName(), request.getFile().getContentType(), request.getFile().getBytes());
        return ResponseEntity.ok().build();
    }

    // TODO PreAuthorize/Validate if the user owns the request with the referencedNumber
    @GetMapping("/list/{referenceNumber}")
    public ResponseEntity getAttachmentList(@PathVariable String referenceNumber) {
        return ResponseEntity.ok(attachmentService.getAttachmentListForReference(referenceNumber));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable String uuid) {
        attachmentService.deleteAttachment(uuid);
        return ResponseEntity.noContent().build();
    }
}
