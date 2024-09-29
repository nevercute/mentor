package pro.nevercute.mentor.remote_camunda_demo.application.port.in.command;

import pro.nevercute.mentor.remote_camunda_demo.domain.model.InvitationResult;

public record HandleNewSignInvitationResultCommand(
        InvitationResult invitationResult,
        String processDefinition,
        String clientId,
        String orderId
) {
}
