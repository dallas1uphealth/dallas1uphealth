package com.oneuphealth.keycloak.extensions.metrics.logger;

import com.oneuphealth.keycloak.extensions.common.annotations.GeneratedIgnoreCoverage;
import com.oneuphealth.keycloak.extensions.metrics.logger.events.KeycloakAdminEvent;
import com.oneuphealth.keycloak.extensions.metrics.logger.events.KeycloakEvent;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class MetricsLoggerEventListenerProvider implements EventListenerProvider {

    private final MetricsService metricsService;

    public MetricsLoggerEventListenerProvider(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public void onEvent(Event event) {
        metricsService.recordMetricsEvent(new KeycloakEvent(event));
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        metricsService.recordMetricsEvent(new KeycloakAdminEvent(adminEvent, includeRepresentation));
    }

    @Override
    @GeneratedIgnoreCoverage
    public void close() {
        // No clean up required
    }
}
