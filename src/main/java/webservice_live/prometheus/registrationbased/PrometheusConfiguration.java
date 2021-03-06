package webservice_live.prometheus.registrationbased;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@EnablePrometheusEndpoint
public class PrometheusConfiguration {

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(5);
    }

}