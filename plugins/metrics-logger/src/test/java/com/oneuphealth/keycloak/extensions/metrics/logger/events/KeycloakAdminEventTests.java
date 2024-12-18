package com.oneuphealth.keycloak.extensions.metrics.logger.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.AuthDetails;
import org.keycloak.events.admin.OperationType;

public class KeycloakAdminEventTests {

    @Test
    public void shouldSetMetricEventTypeToKeycloakAdminEvent() {
        KeycloakAdminEvent keycloakEvent = new KeycloakAdminEvent(new AdminEvent(), false);
        assertEquals(MetricEvent.MetricEventType.KEYCLOAK_ADMIN_EVENT, keycloakEvent.getMetricEventType());
    }

    @Test
    public void shouldSetFieldsFromAdminEvent() {
        AdminEvent adminEvent = new AdminEvent();
        adminEvent.setId("test-event-id");
        adminEvent.setRealmId("test-realm-id");
        adminEvent.setOperationType(OperationType.CREATE);
        adminEvent.setResourcePath("test-resource-path");
        adminEvent.setResourceTypeAsString("test-resource-type-as-string");
        adminEvent.setTime(Instant.now().toEpochMilli());
        adminEvent.setRepresentation("test-representation");
        adminEvent.setError("test-error");

        AuthDetails authDetails = new AuthDetails();
        authDetails.setClientId("test-client-id");
        authDetails.setIpAddress("127.0.0.1");
        authDetails.setRealmId("test-realm-id");
        authDetails.setUserId("test-user-id");

        adminEvent.setAuthDetails(authDetails);

        KeycloakAdminEvent keycloakAdminEvent = new KeycloakAdminEvent(adminEvent, true);
        assertEquals(adminEvent.getId(), keycloakAdminEvent.getEventId());
        assertEquals(adminEvent.getRealmId(), keycloakAdminEvent.getRealmId());
        assertEquals(adminEvent.getOperationType(), keycloakAdminEvent.getOperationType());
        assertEquals(adminEvent.getResourcePath(), keycloakAdminEvent.getResourcePath());
        assertEquals(adminEvent.getResourceTypeAsString(), keycloakAdminEvent.getResourceType());
        assertEquals(adminEvent.getTime(), keycloakAdminEvent.getTimestamp());
        assertEquals(adminEvent.getRepresentation(), keycloakAdminEvent.getRepresentation());
        assertEquals(adminEvent.getError(), keycloakAdminEvent.getError());
        assertEquals(adminEvent.getAuthDetails(), keycloakAdminEvent.getAuthDetails());
    }

    @Test
    public void shouldNotSetRepresentationWhenIncludeRepresentationIsFalse() {
        AdminEvent adminEvent = new AdminEvent();
        adminEvent.setRepresentation("representation");

        KeycloakAdminEvent keycloakAdminEvent = new KeycloakAdminEvent(adminEvent, false);
        assertNull(keycloakAdminEvent.getRepresentation());
    }
}
