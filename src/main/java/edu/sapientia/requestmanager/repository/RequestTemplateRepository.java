package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestTemplateRepository extends CrudRepository<RequestTemplate, Long> {

    List<RequestTemplate> findAll();
}
