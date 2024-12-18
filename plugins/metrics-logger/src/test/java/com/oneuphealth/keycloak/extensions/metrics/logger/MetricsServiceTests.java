package com.oneuphealth.keycloak.extensions.metrics.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneuphealth.keycloak.extensions.metrics.logger.events.MetricEvent;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MetricsServiceTests {

    private final Logger logger = mock(Logger.class);
    private final ObjectMapper objectMapper = mock(ObjectMapper.class);
    private final MetricsService metricsService = new MetricsService(logger, objectMapper);

    @Test
    void logsEventAsInfoLog() throws JsonProcessingException {
        MetricEvent metricEvent = mock(MetricEvent.class);
        String serializedEvent = "serializedEvent!!";
        when(objectMapper.writeValueAsString(metricEvent)).thenReturn(serializedEvent);
        metricsService.recordMetricsEvent(metricEvent);
        verify(logger, times(1)).info(serializedEvent);
    }

    @Test
    void logsErrorMessageWhenObjectMapperThrows() throws JsonProcessingException {
        MetricEvent metricEvent = mock(MetricEvent.class);
        when(objectMapper.writeValueAsString(metricEvent)).thenThrow(JsonProcessingException.class);
        metricsService.recordMetricsEvent(metricEvent);
        verify(logger, times(1)).error(anyString(), any(JsonProcessingException.class));
    }
}
