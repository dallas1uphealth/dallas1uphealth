package com.oneuphealth.keycloak.extensions.metrics.logger;

import com.oneuphealth.keycloak.extensions.metrics.logger.events.MetricEvent;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MetricsService {

    private final Logger logger;
    private final ObjectMapper objectMapper;

    public MetricsService(Logger logger, ObjectMapper objectMapper) {
        this.logger = logger;
        this.objectMapper = objectMapper;
    }

    public void recordMetricsEvent(MetricEvent metricEvent) {
        try {
            logger.info(objectMapper.writeValueAsString(metricEvent));
        } catch (JsonProcessingException ex) {
            logger.error("Unable to write metric event", ex);
        }
    }

}
