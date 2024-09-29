package pro.nevercute.mentor.remote_camunda_demo.application.service;


import lombok.RequiredArgsConstructor;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.NotificationPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.NotificationRequest;
import pro.nevercute.mentor.remote_camunda_demo.configuration.annotation.ApplicationService;

import java.util.Map;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.CLIENT_ID;

@ApplicationService
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationPort notificationPort;
    private final CompleteCamundaExternalTaskService externalTaskService;


    public void sendNewSignNotification(String externalTaskId, Map<String, Object> variables) {
        String clientId = (String) variables.get(CLIENT_ID);
        notificationPort.sendNewContactSignCreated(new NotificationRequest(clientId));
        externalTaskService.completeTaskById(externalTaskId);
    }

    public void sendContactSignCompleted(String externalTaskId, Map<String, Object> variables) {
        String clientId = (String) variables.get(CLIENT_ID);
        notificationPort.sendContactSignCompleted(new NotificationRequest(clientId));
        externalTaskService.completeTaskById(externalTaskId);
    }
}
