package pro.nevercute.mentor.remote_camunda_demo.adapter.in.scheduler.config;

import lombok.RequiredArgsConstructor;
import org.camunda.community.rest.impl.RemoteExternalTaskService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.nevercute.mentor.remote_camunda_demo.adapter.in.scheduler.camunda.ScheduledWorkerAdapter;
import pro.nevercute.mentor.remote_camunda_demo.adapter.out.camunda.config.CamundaClientProperties;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleWorkerTaskUseCase;

@Configuration
@RequiredArgsConstructor
public class ScheduledTaskConfig {

    @Qualifier("remote")
    private final RemoteExternalTaskService taskService;
    private final CamundaClientProperties camundaClientProperties;
    private final HandleWorkerTaskUseCase handleWorkerTaskUseCase;
    private final ScheduledTaskProperties scheduledTaskProperties;
    private final JobScheduler jobScheduler;

    @Bean
    public ScheduledWorkerAdapter jobScheduledWorkerAdapter() {
        ScheduledWorkerAdapter scheduledWorkerAdapter = new ScheduledWorkerAdapter(
                taskService,
                camundaClientProperties,
                handleWorkerTaskUseCase
        );

        jobScheduler.scheduleRecurrently(
                scheduledTaskProperties.getCamundaWorker().getCron(),
                scheduledWorkerAdapter::findAndProcessExternalCamundaTasks
        );
        return scheduledWorkerAdapter;
    }
}
