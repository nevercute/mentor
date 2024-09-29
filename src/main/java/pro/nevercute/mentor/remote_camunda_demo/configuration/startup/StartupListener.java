package pro.nevercute.mentor.remote_camunda_demo.configuration.startup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.repository.DeploymentWithDefinitions;
import org.camunda.community.rest.impl.RemoteRepositoryService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartupListener {

    private final RemoteRepositoryService remoteRepositoryService;

    // ToDo каждый раз деплоится новая версия и для каждой версии свой инстанс в камунде
    // ToDo нужно понять, насколько это вообще нужно
    @EventListener(ContextRefreshedEvent.class)
    public void createDeployment() {
        DeploymentWithDefinitions deploymentWithDefinitions = remoteRepositoryService.createDeployment()
                .addClasspathResource("processes/deduplication_process.bpmn")
                .deployWithResult();
        log.info("Задеплоили процесс. Время деплоя {}, описание {}",
                DateFormat.getInstance().format(deploymentWithDefinitions.getDeploymentTime()),
                new ArrayList<>(deploymentWithDefinitions.getDeployedProcessDefinitions()));
    }
}
