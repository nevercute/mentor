package pro.nevercute.mentor.remote_camunda_demo.application.port.in.command;

public record StartDeduplicationProcessCommand(
        String definition,
        String orderId,
        String clientId
) {
}
