package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Attachment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestAttachmentRepository extends CrudRepository<Attachment, Long> {

    List<Attachment> findAll();
}
