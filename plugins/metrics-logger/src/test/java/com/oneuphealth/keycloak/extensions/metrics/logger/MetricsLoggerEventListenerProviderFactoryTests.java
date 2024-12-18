package com.oneuphealth.keycloak.extensions.metrics.logger;

import org.junit.jupiter.api.Test;
import org.keycloak.models.KeycloakSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

class MetricsLoggerEventListenerProviderFactoryTests {

    private final MetricsLoggerEventListenerProviderFactory metricsEventListenerProviderFactory = new MetricsLoggerEventListenerProviderFactory();

    @Test
    void shouldReturnAnInstanceOfMetricsLoggerEventListenerProvider() {
        assertInstanceOf(MetricsLoggerEventListenerProvider.class,
                metricsEventListenerProviderFactory.create(mock(KeycloakSession.class)));
    }

    @Test
    void shouldReturnTheMetricsLoggerEventListenerProviderFactoryId() {
        assertEquals("metrics-logger", metricsEventListenerProviderFactory.getId());
    }
}
