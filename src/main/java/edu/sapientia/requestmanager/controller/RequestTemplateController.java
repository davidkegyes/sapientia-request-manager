package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestTemplateResponseMapper;
import edu.sapientia.requestmanager.service.RequestTemplateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@CrossOrigin(origins="*")
public class RequestTemplateController {

    private final RequestTemplateService requestTemplateService;

    private final RequestTemplateResponseMapper requestTemplateResponseMapper;

    @GetMapping("/request/templates")
    public ResponseEntity getRequestTemplates(){
        return ResponseEntity.ok(requestTemplateResponseMapper.map(requestTemplateService.findAll()));
    }

}
