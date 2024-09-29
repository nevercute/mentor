package pro.nevercute.mentor.remote_camunda_demo.adapter.out.camunda.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import pro.nevercute.mentor.remote_camunda_demo.domain.model.TaskTopic;

import java.time.Duration;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "adapter.camunda")
@Validated
public class CamundaClientProperties {
    @Positive
    private Integer queryTaskLimit;
    @NotBlank
    private String workerId;
    @NotNull
    private Duration externalTaskLockTime;
    @NotEmpty
    private List<TaskTopic> externalTaskTopics;
}
