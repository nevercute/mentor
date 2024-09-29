package pro.nevercute.mentor.remote_camunda_demo.application.port.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.StartDeduplicationProcessUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.StartDeduplicationProcessCommand;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.CamundaPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.StartProcessRequest;
import pro.nevercute.mentor.remote_camunda_demo.domain.model.VariableKey;

import java.util.Map;

import static pro.nevercute.mentor.remote_camunda_demo.domain.model.ProcessDefinition.DEDUPLICATION_PROCESS;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartDeduplicationProcessImpl implements StartDeduplicationProcessUseCase {

    private final CamundaPort camundaPort;

    @Override
    public void startDeduplicationProcess(StartDeduplicationProcessCommand command) {
        log.info("Обрабатываем команду на создание процесса подписания");
        camundaPort.startProcess(
                new StartProcessRequest(
                        DEDUPLICATION_PROCESS,
                        command.orderId(),
                        Map.of(
                                VariableKey.CLIENT_ID, command.clientId(),
                                VariableKey.ORDER_ID, command.orderId()
                        )
                )
        );
    }
}
