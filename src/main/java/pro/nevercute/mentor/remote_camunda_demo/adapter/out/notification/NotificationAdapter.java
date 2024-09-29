package pro.nevercute.mentor.remote_camunda_demo.adapter.out.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.adapter.out.notification.config.NotificationProperties;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.NotificationPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.NotificationRequest;


@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationAdapter implements NotificationPort {

    private final NotificationProperties notificationProperties;

    @Override
    public void sendNewContactSignCreated(NotificationRequest request) {
        log.info(
                "Отправляем сообщение с templateId {} о том, что нужно подписать заявку клиенту с ID {}",
                notificationProperties.getNewSignTemplateId(),
                request.clientId()
        );
    }

    @Override
    public void sendContactSignCompleted(NotificationRequest request) {
        log.info(
                "Отправляем сообщение с templateId {} о том, что контакт успешно подтвержден для клиента с ID {}",
                notificationProperties.getContactSignCompletedTemplateId(),
                request.clientId()
        );
    }
}
