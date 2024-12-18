package com.oneuphealth.keycloak.extensions.metrics.logger.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.keycloak.events.Event;
import org.keycloak.events.EventType;

class KeycloakEventTests {

    @Test
    void shouldSetMetricEventTypeToKeycloakEvent() {
        KeycloakEvent keycloakEvent = new KeycloakEvent(new Event());
        assertEquals(MetricEvent.MetricEventType.KEYCLOAK_EVENT, keycloakEvent.getMetricEventType());
    }

    @Test
    void shouldPopulateFieldsFromEvent() {
        Event event = new Event();
        event.setUserId("test-user-id");
        event.setRealmId("test-realm-id");
        event.setId("test-event-id");
        event.setType(EventType.CLIENT_INFO);
        event.setDetails(Map.of("key", "value"));
        event.setTime(Instant.now().toEpochMilli());

        KeycloakEvent keycloakEvent = new KeycloakEvent(event);
        assertEquals(event.getUserId(), keycloakEvent.getUserId());
        assertEquals(event.getRealmId(), keycloakEvent.getRealmId());
        assertEquals(event.getId(), keycloakEvent.getEventId());
        assertEquals(event.getType(), keycloakEvent.getEventType());
        assertEquals(event.getDetails(), keycloakEvent.getEventDetails());
        assertEquals(event.getTime(), keycloakEvent.getTimestamp());
    }
}
