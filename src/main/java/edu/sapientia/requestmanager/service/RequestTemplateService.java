package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.RequestTemplateRepository;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class RequestTemplateService {

    private final RequestTemplateRepository repository;

    public List<RequestTemplate> findAll(){
        List<RequestTemplate> templates = new ArrayList<>();
        repository.findAll().forEach(templates::add);
        return templates;
    }

}
