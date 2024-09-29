package pro.nevercute.mentor.remote_camunda_demo.application.port.in;

import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.HandleContactDeduplicationResultCommand;

public interface HandleContactDeduplicationResultUseCase {
    void handle(HandleContactDeduplicationResultCommand command);
}
