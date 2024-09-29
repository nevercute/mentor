package pro.nevercute.mentor.remote_camunda_demo.application.port.out;

import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.SignOnlineRequest;

public interface SignOnlinePort {
    void sendOrder(SignOnlineRequest request);
    void cancelOrder(SignOnlineRequest request);
}
