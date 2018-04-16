package com.lawrencemouarkach.service.autocomplete.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;
import java.util.Objects;

public class MongoDBConfiguration {
    protected static final int DEFAULT_MAX_WAIT_TIME = 30000;
    protected static final int DEFAULT_SERVER_SELECTION_TIMEOUT = 15000;

    protected static final int DEFAULT_HEARTBEAT_FREQUENCY = 10000;
    protected static final int DEFAULT_HEARTBEAT_CONNECT_TIMEOUT = 20000;
    protected static final int DEFAULT_HEARTBEAT_SOCKETT_IMEOUT = 20000;

    protected static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
    protected static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    protected static final int DEFAULT_MAX_BLOCKING_THREADS = 1000;
    protected static final int DEFAULT_MAX_NO_OF_CONNECTIONS = 100;
    public static final String STORE_TYPE_JKS = "JKS";

    protected Map<String, MongoDBDatabase> databases;

    protected String dbName;
    protected String hostPorts;
    protected String deploymentColour;

    protected Integer numberOfConnections = DEFAULT_MAX_NO_OF_CONNECTIONS;
    protected Integer maxBlockingThreads = DEFAULT_MAX_BLOCKING_THREADS;
    protected Integer socketTimeout = DEFAULT_SOCKET_TIMEOUT;
    protected Integer connectTimeout = DEFAULT_CONNECTION_TIMEOUT;
    protected Integer maxWaitTime = DEFAULT_MAX_WAIT_TIME;
    protected Integer serverSelectionTimeout = DEFAULT_SERVER_SELECTION_TIMEOUT;
    protected Integer heartbeatFrequency = DEFAULT_HEARTBEAT_FREQUENCY;
    protected Integer heartbeatConnectTimeout = DEFAULT_HEARTBEAT_CONNECT_TIMEOUT;
    protected Integer heartbeatSocketTimeout = DEFAULT_HEARTBEAT_SOCKETT_IMEOUT;
    protected String writeConcern;
    protected String readPreference;

    protected boolean useSSLconnection = false;
    protected String userNameX509;
    protected String keyStoreType = STORE_TYPE_JKS;
    protected String keyStorePath;
    protected String trustStoreType = STORE_TYPE_JKS;
    protected String trustStorePath;

    public MongoDBConfiguration() {
    }

    public Integer getServerSelectionTimeout() {
        return serverSelectionTimeout;
    }

    public void setServerSelectionTimeout(Integer serverSelectionTimeout) {
        this.serverSelectionTimeout = serverSelectionTimeout;
    }

    public Integer getHeartbeatFrequency() {
        return heartbeatFrequency;
    }

    public void setHeartbeatFrequency(Integer heartbeatFrequency) {
        this.heartbeatFrequency = heartbeatFrequency;
    }

    public Integer getHeartbeatConnectTimeout() {
        return heartbeatConnectTimeout;
    }

    public void setHeartbeatConnectTimeout(Integer heartbeatConnectTimeout) {
        this.heartbeatConnectTimeout = heartbeatConnectTimeout;
    }

    public Integer getHeartbeatSocketTimeout() {
        return heartbeatSocketTimeout;
    }

    public void setHeartbeatSocketTimeout(Integer heartbeatSocketTimeout) {
        this.heartbeatSocketTimeout = heartbeatSocketTimeout;
    }

    /**
     * @return
     * @deprecated method will be removed, when all of the service use the new mongoDB configuration.
     */
    @Deprecated
    @JsonIgnore
    public String getDbName() {
        if (deploymentColour != null && !deploymentColour.isEmpty() && dbName != null) {
            return deploymentColour + "-" + dbName;
        }
        return dbName;
    }

    public Map<String, MongoDBDatabase> getDatabases() {
        return databases;
    }

    public void setDatabases(Map<String, MongoDBDatabase> databases) {
        this.databases = databases;
    }

    /**
     * @param dbName
     * @deprecated method will be removed, when all of the service use the new mongoDB configuration.
     */
    @Deprecated
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDeploymentColour(String deploymentColour) {
        this.deploymentColour = deploymentColour;
    }

    public String getDeploymentColour() {
        return deploymentColour;
    }

    public String getHostPorts() {
        return hostPorts;
    }

    public void setHostPorts(String hostPorts) {
        this.hostPorts = hostPorts;
    }

    public Integer getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(Integer numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }

    public Integer getMaxBlockingThreads() {
        return maxBlockingThreads;
    }

    public void setMaxBlockingThreads(Integer maxBlockingThreads) {
        this.maxBlockingThreads = maxBlockingThreads;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectTimeout() {

        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public String getWriteConcern() {
        return writeConcern;
    }

    public void setWriteConcern(String writeConcern) {
        this.writeConcern = writeConcern;
    }

    public String getReadPreference() {
        return readPreference;
    }

    public void setReadPreference(String readPreference) {
        this.readPreference = readPreference;
    }

    public boolean useSSL() {
        return useSSLconnection;
    }

    public void setUseSSL(Boolean useSSL) {
        this.useSSLconnection = useSSL;
    }

    public String getUserNameX509() {
        return userNameX509;
    }

    public void setUserNameX509(String userNameX509) {
        this.userNameX509 = userNameX509;
    }

    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public String getTrustStoreType() {
        return trustStoreType;
    }

    public void setTrustStoreType(String trustStoreType) {
        this.trustStoreType = trustStoreType;
    }

    public String getTrustStorePath() {
        return trustStorePath;
    }

    public void setTrustStorePath(String trustStorePath) {
        this.trustStorePath = trustStorePath;
    }

    @Override
    public int hashCode() {
        return Objects.hash(databases, dbName, hostPorts, deploymentColour, numberOfConnections, maxBlockingThreads,
                socketTimeout, connectTimeout, maxWaitTime, serverSelectionTimeout, heartbeatFrequency,
                heartbeatConnectTimeout, heartbeatSocketTimeout, writeConcern, readPreference, useSSLconnection,
                userNameX509, keyStoreType, keyStorePath, trustStoreType, trustStorePath);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MongoDBConfiguration other = (MongoDBConfiguration) obj;
        return Objects.equals(this.databases, other.databases)
                && Objects.equals(this.dbName, other.dbName)
                && Objects.equals(this.hostPorts, other.hostPorts)
                && Objects.equals(this.deploymentColour, other.deploymentColour)
                && Objects.equals(this.numberOfConnections, other.numberOfConnections)
                && Objects.equals(this.maxBlockingThreads, other.maxBlockingThreads)
                && Objects.equals(this.socketTimeout, other.socketTimeout)
                && Objects.equals(this.connectTimeout, other.connectTimeout)
                && Objects.equals(this.maxWaitTime, other.maxWaitTime)
                && Objects.equals(this.serverSelectionTimeout, other.serverSelectionTimeout)
                && Objects.equals(this.heartbeatFrequency, other.heartbeatFrequency)
                && Objects.equals(this.heartbeatConnectTimeout, other.heartbeatConnectTimeout)
                && Objects.equals(this.heartbeatSocketTimeout, other.heartbeatSocketTimeout)
                && Objects.equals(this.writeConcern, other.writeConcern)
                && Objects.equals(this.readPreference, other.readPreference)
                && Objects.equals(this.useSSLconnection, other.useSSLconnection)
                && Objects.equals(this.userNameX509, other.userNameX509)
                && Objects.equals(this.keyStoreType, other.keyStoreType)
                && Objects.equals(this.keyStorePath, other.keyStorePath)
                && Objects.equals(this.trustStoreType, other.trustStoreType)
                && Objects.equals(this.trustStorePath, other.trustStorePath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("databases", databases)
                .append("dbName", dbName)
                .append("hostPorts", hostPorts)
                .append("deploymentColour", deploymentColour)
                .append("numberOfConnections", numberOfConnections)
                .append("maxBlockingThreads", maxBlockingThreads)
                .append("socketTimeout", socketTimeout)
                .append("connectTimeout", connectTimeout)
                .append("maxWaitTime", maxWaitTime)
                .append("serverSelectionTimeout", serverSelectionTimeout)
                .append("heartbeatFrequency", heartbeatFrequency)
                .append("heartbeatConnectTimeout", heartbeatConnectTimeout)
                .append("heartbeatSocketTimeout", heartbeatSocketTimeout)
                .append("writeConcern", writeConcern)
                .append("readPreference", readPreference)
                .append("useSSLconnection", useSSLconnection)
                .append("userNameX509", userNameX509)
                .append("keyStoreType", keyStoreType)
                .append("keyStorePath", keyStorePath)
                .append("trustStoreType", trustStoreType)
                .append("trustStorePath", trustStorePath)
                .toString();
    }
}
