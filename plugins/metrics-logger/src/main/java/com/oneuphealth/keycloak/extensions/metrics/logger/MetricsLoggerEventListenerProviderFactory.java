package com.oneuphealth.keycloak.extensions.metrics.logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneuphealth.keycloak.extensions.common.annotations.GeneratedIgnoreCoverage;
import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class MetricsLoggerEventListenerProviderFactory implements EventListenerProviderFactory {

    public static final String ID = "metrics-logger";

    private Logger metricsServiceLogger;
    private ObjectMapper metricsServiceObjectMapper;

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new MetricsLoggerEventListenerProvider(new MetricsService(metricsServiceLogger, metricsServiceObjectMapper));
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    @GeneratedIgnoreCoverage
    public void init(Config.Scope config) {
        metricsServiceLogger =  Logger.getLogger(MetricsService.class);
        metricsServiceObjectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    }

    @Override
    @GeneratedIgnoreCoverage
    public void postInit(KeycloakSessionFactory factory) {
        // Don't need to do anything after initialization
    }

    @Override
    @GeneratedIgnoreCoverage
    public void close() {
        // Don't need to clean anything up
    }
}
