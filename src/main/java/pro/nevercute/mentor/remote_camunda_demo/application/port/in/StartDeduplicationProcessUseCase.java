package pro.nevercute.mentor.remote_camunda_demo.application.port.in;

import pro.nevercute.mentor.remote_camunda_demo.application.port.in.command.StartDeduplicationProcessCommand;

public interface StartDeduplicationProcessUseCase {
    void startDeduplicationProcess(StartDeduplicationProcessCommand command);
}
