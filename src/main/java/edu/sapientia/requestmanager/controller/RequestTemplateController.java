package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.RequestTemplateRequestMapper;
import edu.sapientia.requestmanager.mapper.RequestTemplateResponseMapper;
import edu.sapientia.requestmanager.model.request.RequestTemplateRequest;
import edu.sapientia.requestmanager.service.RequestTemplateService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/template")
@CrossOrigin(origins = "*")
public class RequestTemplateController {

    private final RequestTemplateService requestTemplateService;

    private final RequestTemplateResponseMapper requestTemplateResponseMapper;

    private final RequestTemplateRequestMapper requestMapper;

    @GetMapping("/{uuid}")
    public ResponseEntity getTemplateByUuid(@PathVariable String uuid){
        return ResponseEntity.ok(requestTemplateResponseMapper.map(requestTemplateService.getByUuid(uuid)));
    }

    @GetMapping("/list")
    public ResponseEntity getRequestTemplates() {
        return ResponseEntity.ok(requestTemplateResponseMapper.map(requestTemplateService.findAll()));
    }

    @PostMapping("/save")
    public ResponseEntity saveTemplate(@RequestBody RequestTemplateRequest requestTemplateRequest) {
        return ResponseEntity.ok(
                requestTemplateService.save(requestMapper.mapToEntity(requestTemplateRequest)));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity deleteTemplate(@PathVariable String uuid){
        requestTemplateService.deleteByUuid(uuid);
        return ResponseEntity.accepted().build();
    }

}
