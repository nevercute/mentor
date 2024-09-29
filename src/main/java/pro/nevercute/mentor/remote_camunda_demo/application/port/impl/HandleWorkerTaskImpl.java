package pro.nevercute.mentor.remote_camunda_demo.application.port.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleWorkerTaskUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleWorkerTaskCommand;
import pro.nevercute.mentor.remote_camunda_demo.application.service.EFApprovalService;
import pro.nevercute.mentor.remote_camunda_demo.application.service.NotificationService;
import pro.nevercute.mentor.remote_camunda_demo.application.service.SignOnlineService;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandleWorkerTaskImpl implements HandleWorkerTaskUseCase {

    private final SignOnlineService signOnlineService;
    private final NotificationService notificationService;
    private final EFApprovalService efApprovalService;

    @Override
    public void handle(HandleWorkerTaskCommand command) {
        log.info("Обрабатываем команду HandleWorkerTaskCommand {}", command);
        final String taskId = command.taskId();
        final Map<String, Object> variables = command.variables();
        switch (command.taskTopic()) {
            case SIGN_ONLINE_ORDER_SEND -> signOnlineService.sendNewOrder(taskId, variables);
            case NOTIFICATION_SIGN_START ->
                    notificationService.sendNewSignNotification(taskId, variables);
            case SIGN_ONLINE_ORDER_CANCEL -> signOnlineService.cancelOrder(taskId, variables);
            case DEDUPLICATION_ORDER_UPDATE_STATUS -> efApprovalService.updateOrderStatus(taskId, variables);
            case SIGN_ONLINE_ORDER_START -> efApprovalService.startMobileApproval(taskId, variables);
            case NOTIFICATION_SIGN_FINISHED ->
                    notificationService.sendContactSignCompleted(taskId, variables);
        }
    }
}
