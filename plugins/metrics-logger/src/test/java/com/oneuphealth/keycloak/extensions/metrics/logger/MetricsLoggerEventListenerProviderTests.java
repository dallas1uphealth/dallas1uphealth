package com.oneuphealth.keycloak.extensions.metrics.logger;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.oneuphealth.keycloak.extensions.metrics.logger.events.KeycloakAdminEvent;
import com.oneuphealth.keycloak.extensions.metrics.logger.events.KeycloakEvent;
import org.junit.jupiter.api.Test;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;
import org.mockito.Mockito;



class MetricsLoggerEventListenerProviderTests {

    private final MetricsService mockMetricsService = mock(MetricsService.class);
    private final MetricsLoggerEventListenerProvider metricsLoggerEventListenerProvider = new MetricsLoggerEventListenerProvider(
            mockMetricsService);

    @Test
    void shouldRecordKeycloakEventMetrics() {
        metricsLoggerEventListenerProvider.onEvent(new Event());
        verify(mockMetricsService, times(1)).recordMetricsEvent(Mockito.any(KeycloakEvent.class));
    }

    @Test
    void shouldRecordKeycloakAdminEventMetrics() {
        metricsLoggerEventListenerProvider.onEvent(new AdminEvent(), false);
        verify(mockMetricsService, times(1)).recordMetricsEvent(Mockito.any(KeycloakAdminEvent.class));
    }
}
