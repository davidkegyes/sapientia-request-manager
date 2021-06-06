package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.mapper.RequestTemplateResponseMapper;
import edu.sapientia.requestmanager.model.response.RequestResponse;
import edu.sapientia.requestmanager.model.response.RequestTemplateResponse;
import edu.sapientia.requestmanager.repository.RequestTemplateRepository;
import edu.sapientia.requestmanager.repository.entity.RequestTemplate;
import edu.sapientia.requestmanager.service.RequestService;
import edu.sapientia.requestmanager.service.RequestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RestTestController {

    @Autowired
    private RequestTemplateRepository requestTemplateRepository;

    @Autowired
    private RequestTemplateService requestTemplateService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RequestTemplateResponseMapper requestTemplateResponseMapper;


    @RequestMapping("/test")
    public ResponseEntity<List<RequestResponse>> helloWorld(){
        return ResponseEntity.ok(requestMapper.mapToResponseList(requestService.getRequestsByUserId(8L)));
    }

    @RequestMapping("/test/requestTemplates")
    public ResponseEntity<List<RequestTemplateResponse>> getRequestTemplates(){
        return ResponseEntity.ok(requestTemplateResponseMapper.map(requestTemplateService.findAll()));
    }

    @CrossOrigin
    @PostMapping("/application")
    public void createApplication(@RequestBody Map<String, Object> test){
        System.out.println("Application Json Uploaded");
    }
}
