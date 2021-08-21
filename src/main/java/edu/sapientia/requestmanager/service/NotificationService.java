package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.entity.Request;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Data
@Slf4j
@Service
public class NotificationService {

    private final MailService mailService;

    private final MessageSource messageSource;

    private final UserService userService;

    @Value("${ui.inspect-url}")
    private String uiInspectUrl;

    @Async
    public void notifySecretaryOfNewRequest(Request request) {
        Locale locale = Locale.US;
        try {
            mailService.sendMail(
                    userService.getAllSecretaryEmailAddresses().toArray(new String[0]),
                    messageSource.getMessage("mail.subject.secretary.new", null, locale),
                    messageSource.getMessage("mail.body.secretary.new",
                            new String[]{request.getUser().getFirstname().concat(" ").concat(request.getUser().getLastname()), request.getName(), uiInspectUrl.concat(request.getReferenceNumber())},
                            locale
                    )
            );
        } catch (Exception e) {
            log.error("Error during mail sending", e);
        }
    }

    @Async
    public void notifyOwnerRequestStatusChanged(Request request) {
        Locale locale = Locale.US;
        String to = request.getUser().getEmail();
        String subject = "";
        String body = "";
        String status = messageSource.getMessage("request.status.".concat(request.getStatus().name().toLowerCase()).toLowerCase(), null, Locale.ENGLISH);
        switch (request.getStatus()) {
            case NEW:
                subject = messageSource.getMessage("mail.subject.request.new", null, locale);
                body = messageSource.getMessage("mail.body.request.new",
                        new String[]{request.getName(), uiInspectUrl.concat(request.getReferenceNumber())},
                        locale
                );
                notifySecretaryOfNewRequest(request);
                break;
            case INCOMPLETE:
                subject = messageSource.getMessage("mail.subject.request.incomplete",
                        new String[]{status},
                        locale);
                body = messageSource.getMessage("mail.body.request.incomplete",
                        new String[]{request.getName(), String.join("\n\r", request.getRequiredDocuments()), uiInspectUrl.concat(request.getReferenceNumber())},
                        locale
                );
                break;
            default:
                subject = messageSource.getMessage("mail.subject.request.processed",
                        new String[]{status},
                        locale);
                body = messageSource.getMessage("mail.body.request.processed",
                        new String[]{status, request.getOfficialReferenceNumber(), request.getInspectorUser() != null ? request.getInspectorUser().getEmail() : "System", uiInspectUrl.concat(request.getReferenceNumber())},
                        locale
                );
        }
        try {
            mailService.sendMail(new String[]{to}, subject, body);
        } catch (Exception e) {
            log.error("Error during mail sending", e);
        }
    }
}
