package pro.nevercute.mentor.remote_camunda_demo.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum TaskTopic {
    SIGN_ONLINE_ORDER_SEND("sign-online.order.send"),
    SIGN_ONLINE_ORDER_CANCEL("sign-online.order.cancel"),
    DEDUPLICATION_ORDER_UPDATE_STATUS("deduplication.order.update_status"),
    SIGN_ONLINE_ORDER_START("sign-online.order.start"),
    NOTIFICATION_SIGN_START("notification.sign.start"),
    NOTIFICATION_SIGN_FINISHED("notification.sign.finished");

    private final String topicName;

    public static TaskTopic findByTopicName(String topicName) {
        return Arrays.stream(values()).filter(it -> it.topicName.equals(topicName)).findFirst().orElse(null);
    }
}
