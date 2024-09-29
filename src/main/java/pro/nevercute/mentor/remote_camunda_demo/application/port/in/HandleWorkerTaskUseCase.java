package pro.nevercute.mentor.remote_camunda_demo.application.port.in;

import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleWorkerTaskCommand;

public interface HandleWorkerTaskUseCase {
    void handle(HandleWorkerTaskCommand command);
}
