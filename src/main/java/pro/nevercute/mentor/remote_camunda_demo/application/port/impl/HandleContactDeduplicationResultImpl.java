package pro.nevercute.mentor.remote_camunda_demo.application.port.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleContactDeduplicationResultUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleContactDeduplicationResultCommand;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.CamundaPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CorrelateMessageRequest;
import pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey;

import java.util.Map;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.MessageDefinition.DEDUPLICATION_RESULT;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandleContactDeduplicationResultImpl implements HandleContactDeduplicationResultUseCase {

    private final CamundaPort camundaPort;

    @Override
    public void handle(HandleContactDeduplicationResultCommand command) {
        log.info("Обрабатываем команду на подтверждением клиентом процесса подписания");
        camundaPort.correlateMessage(
                new CorrelateMessageRequest(
                        DEDUPLICATION_RESULT,
                        command.orderId(),
                        Map.of(VariableKey.DEDUPLICATION_RESULT, command.deduplicationResult().name())
                )
        );
    }
}
