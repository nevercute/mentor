package pro.nevercute.mentor.remote_camunda_demo.adapter.in.scheduler.camunda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.community.rest.impl.RemoteExternalTaskService;
import org.camunda.community.rest.impl.builder.RemoteExternalTaskQueryBuilder;
import org.jobrunr.jobs.annotations.Job;
import pro.nevercute.mentor.remote_camunda_demo.adapter.out.camunda.config.CamundaClientProperties;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.HandleWorkerTaskUseCase;
import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleWorkerTaskCommand;
import pro.nevercute.mentor.remote_camunda_demo.domain.model.TaskTopic;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ScheduledWorkerAdapter {

    private final RemoteExternalTaskService taskService;
    private final CamundaClientProperties camundaClientProperties;
    private final HandleWorkerTaskUseCase handleWorkerTaskUseCase;

    @Job(name = "Постоянная задача поиска и выполнения задач для связанных процессов")
    public void findAndProcessExternalCamundaTasks() {
        log.info("Стартуем external tasks worker");
        RemoteExternalTaskQueryBuilder externalTaskQueryTopicBuilder = taskService
                .fetchAndLock(camundaClientProperties.getQueryTaskLimit(), camundaClientProperties.getWorkerId());

        long externalTaskLockTime = camundaClientProperties.getExternalTaskLockTime().toMillis();
        camundaClientProperties.getExternalTaskTopics()
                .forEach(topic -> externalTaskQueryTopicBuilder.topic(topic.getTopicName(), externalTaskLockTime));

        List<LockedExternalTask> tasks = externalTaskQueryTopicBuilder.execute();
        log.info("Количество найденных external tasks для выполнения: {}", tasks.size());

        tasks.forEach(task -> {
            TaskTopic topic = TaskTopic.findByTopicName(task.getTopicName());
            if (topic != null) {
                handleWorkerTaskUseCase.handle(
                        new HandleWorkerTaskCommand(
                                topic,
                                task.getId(),
                                task.getBusinessKey(),
                                task.getVariables()
                        )
                );
            } else {
                log.error("Не удалось определить топик задачи по имени, имя топика {}", task.getTopicName());
            }
        });
    }
}
