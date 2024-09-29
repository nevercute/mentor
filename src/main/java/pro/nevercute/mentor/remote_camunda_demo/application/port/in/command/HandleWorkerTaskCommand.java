package pro.nevercute.mentor.remote_camunda_demo.application.port.in.command;

import pro.nevercute.mentor.remote_camunda_demo.domain.model.TaskTopic;

import java.util.Map;

public record HandleWorkerTaskCommand(
        TaskTopic taskTopic,
        String taskId,
        String businessKey,
        Map<String, Object> variables
) {
}
