package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestMapper;
import edu.sapientia.requestmanager.model.response.RequestResponse;
import edu.sapientia.requestmanager.repository.RequestTemplateRepository;
import edu.sapientia.requestmanager.service.RequestService;
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
    private RequestService requestService;

    @Autowired
    private RequestMapper requestMapper;


    @RequestMapping("/test")
    public ResponseEntity<List<RequestResponse>> helloWorld(){
        return ResponseEntity.ok(requestMapper.mapToResponseList(requestService.getRequestsByUserId(8L)));
    }

    @CrossOrigin
    @PostMapping("/application")
    public void createApplication(@RequestBody Map<String, Object> test){
        System.out.println("Application Json Uploaded");
    }
}
