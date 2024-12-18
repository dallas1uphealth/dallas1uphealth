package com.oneuphealth.keycloak.extensions.metrics.logger.events;

import java.util.Map;

import org.keycloak.events.Event;
import org.keycloak.events.EventType;

public class KeycloakEvent extends MetricEvent {

    private final String userId;
    private final String realmId;
    private final String eventId;
    private final EventType eventType;
    private final Map<String, String> eventDetails;
    private final Long timestamp;

    public KeycloakEvent(Event event) {
        super(MetricEventType.KEYCLOAK_EVENT);
        this.userId = event.getUserId();
        this.realmId = event.getRealmId();
        this.eventId = event.getId();
        this.eventType = event.getType();
        this.eventDetails = event.getDetails();
        this.timestamp = event.getTime();
    }

    public String getUserId() {
        return userId;
    }

    public String getRealmId() {
        return realmId;
    }

    public String getEventId() {
        return eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Map<String, String> getEventDetails() {
        return eventDetails;
    }

    public Long getTimestamp() {
        return timestamp;
    }

}
