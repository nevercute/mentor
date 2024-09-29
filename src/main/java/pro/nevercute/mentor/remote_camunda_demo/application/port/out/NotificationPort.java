package pro.nevercute.mentor.remote_camunda_demo.application.port.out;

import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.NotificationRequest;

public interface NotificationPort {
    void sendNewContactSignCreated(NotificationRequest request);
    void sendContactSignCompleted(NotificationRequest request);
}
