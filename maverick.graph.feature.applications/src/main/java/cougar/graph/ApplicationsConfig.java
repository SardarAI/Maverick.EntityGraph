package cougar.graph;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(
        name = "application.features.modules.applications",
        matchIfMissing = false)
@ComponentScan(basePackages = "cougar.graph.feature.applications")
@Slf4j(topic = "graph.feature.apps")
public class ApplicationsConfig {

    @PostConstruct
    public void logActivation() {
        log.info("Activated Feature: Multi-tenancy through Subscriptions");
    }
}
