package pro.nevercute.mentor.remote_camunda_demo.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.CamundaPort;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CompleteExternalTaskRequest;
import pro.nevercute.mentor.remote_camunda_demo.configuration.annotation.ApplicationService;

@ApplicationService
@RequiredArgsConstructor
@Slf4j
public class CompleteCamundaExternalTaskService {

    private final CamundaPort camundaPort;

    public void completeTaskById(String externalTaskId) {
        camundaPort.completeExternalTask(new CompleteExternalTaskRequest(externalTaskId));

    }
}
