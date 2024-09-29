package pro.nevercute.mentor.remote_camunda_demo.domain.model;

public final class VariableKey {
    private VariableKey() {
        throw new UnsupportedOperationException();
    }

    public static final String CLIENT_ID = "clientId";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_STATUS = "orderStatus";
    public static final String INVITATION_RESULT = "invitationResult";
    public static final String DEDUPLICATION_RESULT = "deduplicationResult";
}
