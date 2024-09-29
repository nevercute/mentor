package pro.nevercute.mentor.remote_camunda_demo.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.SignOnlineRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.SignOnlinePort;
import pro.nevercute.mentor.remote_camunda_demo.configuration.annotation.ApplicationService;

import java.util.Map;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.CLIENT_ID;
import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.ORDER_ID;

@ApplicationService
@RequiredArgsConstructor
@Slf4j
public class SignOnlineService {

    private final SignOnlinePort signOnlinePort;
    private final CompleteCamundaExternalTaskService externalTaskService;

    public void sendNewOrder(String externalTaskId, Map<String, Object> variables) {
        sendSignOnlineOrder(variables);
        externalTaskService.completeTaskById(externalTaskId);
    }

    public void cancelOrder(String externalTaskId, Map<String, Object> variables) {
        cancelSignOnlineOrder(variables);
        externalTaskService.completeTaskById(externalTaskId);
    }

    private void sendSignOnlineOrder(Map<String, Object> variables) {
        String clientId = (String) variables.get(CLIENT_ID);
        String orderId = (String) variables.get(ORDER_ID);
        SignOnlineRequest request = new SignOnlineRequest(clientId, orderId);
        signOnlinePort.sendOrder(request);
    }

    private void cancelSignOnlineOrder(Map<String, Object> variables) {
        String clientId = (String) variables.get(CLIENT_ID);
        String orderId = (String) variables.get(ORDER_ID);
        SignOnlineRequest request = new SignOnlineRequest(clientId, orderId);
        signOnlinePort.cancelOrder(request);
    }
}
