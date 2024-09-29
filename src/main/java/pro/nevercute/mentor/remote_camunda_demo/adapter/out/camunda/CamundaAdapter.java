package pro.nevercute.mentor.remote_camunda_demo.adapter.out.camunda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.community.rest.impl.RemoteExternalTaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pro.nevercute.mentor.remote_camunda_demo.adapter.out.camunda.config.CamundaClientProperties;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.CamundaPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CorrelateMessageRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CompleteExternalTaskRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.StartProcessRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class CamundaAdapter implements CamundaPort {

    @Qualifier("remote")
    private final RuntimeService runtimeService;
    @Qualifier("remote")
    private final RemoteExternalTaskService taskService;
    private final CamundaClientProperties camundaClientProperties;

    @Override
    public void startProcess(StartProcessRequest request) {
        log.info(
                "Отправляем в camunda запрос на запуск процесса {} с ID {}",
                request.processDefinition(),
                request.businessKeyId()
        );
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(
                        request.processDefinition(),
                        request.businessKeyId(),
                        request.variables()
                );
        log.info(
                "Стартовали процесс {} с ID {}",
                processInstance.getProcessDefinitionId(),
                processInstance.getBusinessKey()
        );
    }

    @Override
    public void correlateMessage(CorrelateMessageRequest request) {
        runtimeService.createMessageCorrelation(request.receiverMessageName())
                .processInstanceBusinessKey(request.businessKeyId())
                .setVariables(request.variables())
                .correlate();
    }

    @Override
    public void completeExternalTask(CompleteExternalTaskRequest request) {
        log.info("Завершаем external task ID {}", request.externalTaskId());
        taskService.complete(request.externalTaskId(), camundaClientProperties.getWorkerId());
    }
}
