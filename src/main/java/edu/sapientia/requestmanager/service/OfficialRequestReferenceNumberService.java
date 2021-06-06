package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.RequestRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Service
public class OfficialRequestReferenceNumberService {

    private final RequestRepository requestRepository;

    public String getNewOfficialReferenceNumber(){
        String currentDate = DateTimeFormatter.ofPattern("uuuu-MM-dd").format(LocalDate.now());
        Long count = requestRepository.countByOfficialReferenceNumberIsLike(currentDate);
        return currentDate.concat("/").concat(String.valueOf(1L + count));
    }
}
