package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {

    List<Attachment> getAttachmentByRequestReferenceNumber(String requestReferenceNumber);

    @Transactional
    void deleteByUuid(String uuid);
}
