package pro.nevercute.mentor.remote_camunda_demo.application.port.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleNewSignInvitationResultUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleNewSignInvitationResultCommand;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.CamundaPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CorrelateMessageRequest;

import java.util.Map;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.MessageDefinition.NEW_SIGN_INVITATION_RESULT;
import static pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey.INVITATION_RESULT;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandleNewSignInvitationResultImpl implements HandleNewSignInvitationResultUseCase {

    private final CamundaPort camundaPort;

    @Override
    public void handle(HandleNewSignInvitationResultCommand command) {
        log.info("Обрабатываем команду на подтверждением клиентом процесса подписания");
        camundaPort.correlateMessage(
                new CorrelateMessageRequest(
                        NEW_SIGN_INVITATION_RESULT,
                        command.orderId(),
                        Map.of(INVITATION_RESULT, command.invitationResult().name())
                )
        );
    }
}
