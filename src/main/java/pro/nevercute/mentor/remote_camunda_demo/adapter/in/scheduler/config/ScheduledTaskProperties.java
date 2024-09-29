package pro.nevercute.mentor.remote_camunda_demo.adapter.in.scheduler.config;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import pro.nevercute.mentor.remote_camunda_demo.adapter.in.scheduler.camunda.ScheduledWorkerProperties;

@Data
@Component
@ConfigurationProperties(prefix = "scheduling")
@Validated
public class ScheduledTaskProperties {

    @Valid
    private ScheduledWorkerProperties camundaWorker;
}
