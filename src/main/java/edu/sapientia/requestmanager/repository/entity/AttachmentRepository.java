package edu.sapientia.requestmanager.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    List<Attachment> getAttachmentByRequestReferenceNumber(String requestReferenceNumber);
}
