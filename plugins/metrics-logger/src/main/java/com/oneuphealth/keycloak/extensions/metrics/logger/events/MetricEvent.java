package com.oneuphealth.keycloak.extensions.metrics.logger.events;

public abstract class MetricEvent {

    private final MetricEventType metricEventType;

    protected MetricEvent(MetricEventType metricEventType) {
        this.metricEventType = metricEventType;
    }

    protected MetricEventType getMetricEventType() {
        return metricEventType;
    }

    protected enum MetricEventType {
        CREATE_REQUEST_TOKEN, KEYCLOAK_ADMIN_EVENT, KEYCLOAK_EVENT;
    }
}
