package pro.nevercute.mentor.remote_camunda_demo.application.port.in;

import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleNewSignInvitationResultCommand;

public interface HandleNewSignInvitationResultUseCase {
    void handle(HandleNewSignInvitationResultCommand command);
}
