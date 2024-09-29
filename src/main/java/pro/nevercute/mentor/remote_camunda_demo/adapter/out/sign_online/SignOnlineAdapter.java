package pro.nevercute.mentor.remote_camunda_demo.adapter.out.sign_online;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.SignOnlineRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.SignOnlinePort;

@Component
@Slf4j
public class SignOnlineAdapter implements SignOnlinePort {

    @Override
    public void sendOrder(SignOnlineRequest request) {
        log.info("Отправляем заявку {} в sign-online для клиента с id {}", request.orderId(), request.clientId());
    }

    @Override
    public void cancelOrder(SignOnlineRequest request) {
        log.info("Отменяем заявку {} в sign-online для клиента с id {}", request.orderId(), request.clientId());
    }
}
