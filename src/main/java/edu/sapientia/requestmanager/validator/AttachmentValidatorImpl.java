package edu.sapientia.requestmanager.validator;


import edu.sapientia.requestmanager.repository.entity.Attachment;
import edu.sapientia.requestmanager.service.AttachmentService;
import edu.sapientia.requestmanager.service.RequestService;
import lombok.Data;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Data
public class AttachmentValidatorImpl implements ConstraintValidator<AttachmentReferenceValidator, String> {

    private final AttachmentService attachmentService;
    private final RequestService requestService;

    @Override
    public boolean isValid(String referenceNumber, ConstraintValidatorContext constraintValidatorContext) {
//        Optional<Attachment> attachment = attachmentService.findByReferenceId(referenceNumber);
//        if (attachment.isPresent()) {
//            requestService.findRequest(attachment.get().getRequestReferenceNumber())
//        }
        return false;
    }
}
