package pro.nevercute.mentor.remote_camunda_demo.domain.model;

public final class MessageDefinition {
    private MessageDefinition() {
        throw new UnsupportedOperationException();
    }

    public static final String NEW_SIGN_INVITATION_RESULT = "NEW_SIGN_INVITATION_RESULT";
    public static final String DEDUPLICATION_RESULT = "DEDUPLICATION_RESULT";
}
