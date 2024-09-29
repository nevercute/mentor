package pro.nevercute.mentor.remote_camunda_demo.adapter.in.scheduler.camunda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Data
@Validated
public class ScheduledWorkerProperties {
    @NotBlank
    private String cron;
    @NotNull
    private Duration expiredPeriod;
}
