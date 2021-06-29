package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.RequestTemplateRepository;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class RequestTemplateService {

    private final RequestTemplateRepository repository;

    public List<RequestTemplate> findAll() {
        List<RequestTemplate> templates = new ArrayList<>();
        repository.findAll().forEach(templates::add);
        return templates;
    }

    public RequestTemplate save(RequestTemplate requestTemplate) {
        return repository.save(requestTemplate);
    }

    public void deleteByUuid(String uuid) {
        repository.deleteByUuid(uuid);
    }

    public RequestTemplate getByUuid(String uuid) {
        return repository.findByUuid(uuid);
    }
}
