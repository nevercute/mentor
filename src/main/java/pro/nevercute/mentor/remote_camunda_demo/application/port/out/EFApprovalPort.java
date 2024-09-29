package pro.nevercute.mentor.remote_camunda_demo.application.port.out;

import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.StartMobileApprovalRequest;
import pro.nevercute.mentor.remote_camunda_demo.application.port.out.request.UpdateStatusRequest;

public interface EFApprovalPort {
    void updateStatus(UpdateStatusRequest request);
    void startMobileApproval(StartMobileApprovalRequest request);
}
