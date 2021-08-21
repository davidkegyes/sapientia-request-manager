package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.lang.ref.Reference;
import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {

    List<Attachment> getAttachmentByRequestReferenceNumber(String requestReferenceNumber);

    int countAllByRequestReferenceNumber(String referenceNumber);

    @Transactional
    void deleteByUuid(String uuid);
}
