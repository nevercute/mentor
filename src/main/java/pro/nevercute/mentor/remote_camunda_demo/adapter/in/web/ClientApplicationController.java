package pro.nevercute.mentor.remote_camunda_demo.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.nevercute.mentor.remote_camunda_demo.adapter.in.web.dto.DeduplicationResultDto;
import pro.nevercute.mentor.remote_camunda_demo.adapter.in.web.dto.NewSignInvitationResultDto;
import pro.nevercute.mentor.remote_camunda_demo.adapter.in.web.dto.OrderDetailsDto;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleContactDeduplicationResultUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleNewSignInvitationResultUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.StartDeduplicationProcessUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleContactDeduplicationResultCommand;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleNewSignInvitationResultCommand;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.StartDeduplicationProcessCommand;
import pro.nevercute.mentor.remote_camunda_demo.domain.model.DeduplicationResult;
import pro.nevercute.mentor.remote_camunda_demo.domain.model.InvitationResult;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.ProcessDefinition.DEDUPLICATION_PROCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientApplicationController {

    private final StartDeduplicationProcessUseCase startDeduplicationProcessUseCase;
    private final HandleNewSignInvitationResultUseCase handleNewSignInvitationResultUseCase;
    private final HandleContactDeduplicationResultUseCase handleContactDeduplicationResultUseCase;

    @PostMapping("/start-deduplication-process")
    public void createClientSignProcess(@RequestBody OrderDetailsDto orderDetailsDto) {
        log.info("Пришел rest запрос на запуск процесс подписания клиентом {}", orderDetailsDto.clientId());
        startDeduplicationProcessUseCase.startDeduplicationProcess(
                new StartDeduplicationProcessCommand(
                        DEDUPLICATION_PROCESS,
                        orderDetailsDto.orderId(),
                        orderDetailsDto.clientId()
                )
        );
    }

    @PostMapping("/new-sign-invitation-result")
    public void acceptSignMt(@RequestBody NewSignInvitationResultDto dto) {
        log.info("Пришел rest запрос на подтверждение процесса подписания клиентом {}", dto.clientId());
        handleNewSignInvitationResultUseCase.handle(
                new HandleNewSignInvitationResultCommand(
                        InvitationResult.valueOf(dto.invitationResult()),
                        DEDUPLICATION_PROCESS,
                        dto.clientId(),
                        dto.orderId())
        );
    }

    @PostMapping("/deduplication-result")
    public void handleContactDeduplicationResult(@RequestBody DeduplicationResultDto dto) {
        log.info("Пришел rest запрос на подтверждение дедупликации контакта для клиента {}", dto.clientId());
        handleContactDeduplicationResultUseCase.handle(
                new HandleContactDeduplicationResultCommand(
                        DeduplicationResult.valueOf(dto.deduplicationResult()),
                        DEDUPLICATION_PROCESS,
                        dto.clientId(),
                        dto.orderId())
        );
    }
}
