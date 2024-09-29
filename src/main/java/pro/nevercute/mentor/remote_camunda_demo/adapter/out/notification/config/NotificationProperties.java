package pro.nevercute.mentor.remote_camunda_demo.adapter.out.notification.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@ConfigurationProperties(prefix = "adapter.notification")
@Validated
public class NotificationProperties {

    @NotBlank
    private String newSignTemplateId;
    @NotBlank
    private String contactSignCompletedTemplateId;
}
