package pro.nevercute.mentor.remote_camunda_demo.application.port.out.request;

import java.util.Map;

public record StartProcessRequest(
        String processDefinition,
        String businessKeyId,
        Map<String, Object> variables
) {
}
