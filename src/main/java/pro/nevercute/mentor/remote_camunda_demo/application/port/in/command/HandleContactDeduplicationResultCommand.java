package pro.nevercute.mentor.remote_camunda_demo.application.port.in.command;

import pro.nevercute.mentor.remote_camunda_demo.domain.model.DeduplicationResult;

public record HandleContactDeduplicationResultCommand(
        DeduplicationResult deduplicationResult,
        String processDefinition,
        String clientId,
        String orderId
) {
}
