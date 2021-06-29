package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RequestTemplateRepository extends CrudRepository<RequestTemplate, String> {

    List<RequestTemplate> findAll();

    @Transactional
    void deleteByUuid(String uuid);

    RequestTemplate findByUuid(String uuid);
}