package pro.nevercute.mentor.remote_camunda_demo.adapter.out.ef_approval;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.EFApprovalPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.StartMobileApprovalRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.UpdateStatusRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class EFApprovalAdapter implements EFApprovalPort {
    @Override
    public void updateStatus(UpdateStatusRequest request) {
        log.info(
                "Отправляем обновленный статус заявки с ID {} в ЕФ.Подтверждение, статус {}",
                request.orderId(),
                request.orderStatus()
        );
    }

    @Override
    public void startMobileApproval(StartMobileApprovalRequest request) {
        log.info(
                "Отправляем запрос на начало подтверждения в МТ заявки с ID {}, клиент ID {}",
                request.orderId(),
                request.clientId()
        );
    }
}
