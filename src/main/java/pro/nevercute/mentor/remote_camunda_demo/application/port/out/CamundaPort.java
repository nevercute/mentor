package pro.nevercute.mentor.remote_camunda_demo.application.port.out;

import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CorrelateMessageRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.CompleteExternalTaskRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.StartProcessRequest;

public interface CamundaPort {
    void startProcess(StartProcessRequest request);
    void correlateMessage(CorrelateMessageRequest request);
    void completeExternalTask(CompleteExternalTaskRequest request);
}
