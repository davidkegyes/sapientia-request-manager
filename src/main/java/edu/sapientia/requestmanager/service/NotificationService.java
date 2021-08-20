package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.entity.Request;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Data @Slf4j
@Service
public class NotificationService {

    private final MailService mailService;

    private final MessageSource messageSource;

    @Value("${ui.inspect-url}")
    private String uiInspectUrl;

    @Async
    public void sendRequestProcessedMail(Request request){
        Locale locale = Locale.US;
        try {
            String status = messageSource.getMessage("request.status.".concat(request.getStatus().name().toLowerCase()).toLowerCase(), null, Locale.ENGLISH);
            mailService.sendMail(
                    request.getUser().getEmail(),
                    messageSource.getMessage("mail.subject.request.processed",
                            new String[]{status},
                            locale),
                    messageSource.getMessage("mail.body.request.processed",
                            new String[]{status, request.getInspectorUser().getEmail(), uiInspectUrl.concat(request.getReferenceNumber())},
                            locale
                    )
            );
        }catch (Exception e) {
            log.error("Error during mail sending", e);
        }
    }

}
