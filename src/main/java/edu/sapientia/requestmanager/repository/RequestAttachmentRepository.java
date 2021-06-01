package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.RequestAttachment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestAttachmentRepository extends CrudRepository<RequestAttachment, Long> {

    List<RequestAttachment> findAll();

    List<RequestAttachment> findByUserIdAndReferenceNumber(Long userId, String referenceNumber);

}
