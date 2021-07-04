package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByUserIdOrderByCreateDateTimeDesc(Long userId);

    Request findByUserIdAndReferenceNumber(Long userId, String referenceNumber);

    Request findByReferenceNumber(String referenceNumber);

    Long countByOfficialReferenceNumberIsLike(String refNumberPart);

    @Query("select distinct r from Request r where r.user.id = ?1 and (r.referenceNumber = ?2 or r.officialReferenceNumber = ?2)")
    Request findByUserIdAndReferenceNumberOrOfficialReferenceNumber(Long userId, String referenceNumber);

}
