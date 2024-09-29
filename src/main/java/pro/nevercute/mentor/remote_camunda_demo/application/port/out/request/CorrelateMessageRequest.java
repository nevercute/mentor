package pro.nevercute.mentor.remote_camunda_demo.application.port.out.request;

import java.util.Map;

public record CorrelateMessageRequest(
        String receiverMessageName,
        String businessKeyId,
        Map<String, Object> variables
) {
}
