package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {

    List<Request> findAllByUserIdOrderByReferenceNumberDesc(Long userId);

    Request findByUserIdAndReferenceNumber(Long userId, String referenceNumber);

}
