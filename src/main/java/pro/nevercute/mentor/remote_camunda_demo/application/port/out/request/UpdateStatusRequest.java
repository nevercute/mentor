package pro.nevercute.mentor.remote_camunda_demo.application.port.out.request;

public record UpdateStatusRequest(String orderId, String orderStatus) {
}
