package com.oneuphealth.keycloak.extensions.metrics.logger.events;

import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.AuthDetails;
import org.keycloak.events.admin.OperationType;

public class KeycloakAdminEvent extends MetricEvent {

    private final String eventId;
    private final String realmId;
    private final OperationType operationType;
    private final String resourceType;
    private final String resourcePath;
    private final String representation;
    private final String error;
    private final AuthDetails authDetails;
    private final Long timestamp;

    public KeycloakAdminEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        super(MetricEventType.KEYCLOAK_ADMIN_EVENT);
        this.eventId = adminEvent.getId();
        this.realmId = adminEvent.getRealmId();
        this.operationType = adminEvent.getOperationType();
        this.resourceType = adminEvent.getResourceTypeAsString();
        this.resourcePath = adminEvent.getResourcePath();
        this.error = adminEvent.getError();
        this.authDetails = adminEvent.getAuthDetails();
        this.timestamp = adminEvent.getTime();
        this.representation = includeRepresentation ? adminEvent.getRepresentation() : null;
    }

    public String getEventId() {
        return eventId;
    }

    public String getRealmId() {
        return realmId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getRepresentation() {
        return representation;
    }

    public String getError() {
        return error;
    }

    public AuthDetails getAuthDetails() {
        return authDetails;
    }

    public Long getTimestamp() {
        return timestamp;
    }

}
