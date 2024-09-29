package pro.nevercute.mentor.remote_camunda_demo.application.service;

import lombok.RequiredArgsConstructor;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.EFApprovalPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.StartMobileApprovalRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.UpdateStatusRequest;
import pro.nevercute.mentor.remote_camunda_demo.configuration.annotation.ApplicationService;

import java.util.Map;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.CLIENT_ID;
import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.ORDER_ID;
import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.ORDER_STATUS;

@ApplicationService
@RequiredArgsConstructor

public class EFApprovalService {

    private final EFApprovalPort efApprovalPort;
    private final CompleteCamundaExternalTaskService externalTaskService;


    public void updateOrderStatus(String taskId, Map<String, Object> variables) {
        String orderId = (String) variables.get(ORDER_ID);
        String orderStatus = (String) variables.get(ORDER_STATUS);
        efApprovalPort.updateStatus(
                new UpdateStatusRequest(
                        orderId,
                        orderStatus
                )
        );
        externalTaskService.completeTaskById(taskId);
    }

    public void startMobileApproval(String taskId, Map<String, Object> variables) {
        String orderId = (String) variables.get(ORDER_ID);
        String clientId = (String) variables.get(CLIENT_ID);
        efApprovalPort.startMobileApproval(
                new StartMobileApprovalRequest(
                        orderId,
                        clientId
                )
        );
        externalTaskService.completeTaskById(taskId);
    }
}
