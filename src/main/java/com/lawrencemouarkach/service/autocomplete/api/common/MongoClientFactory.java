package com.lawrencemouarkach.service.autocomplete.api.common;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoClientFactory {
    private static final Logger LOG = LoggerFactory.getLogger(MongoClientFactory.class);

    private static final WriteConcern DEFAULT_WRITE_CONCERN = WriteConcern.NORMAL;
    private static final ReadPreference DEFAULT_READ_PREFERENCE = ReadPreference.primary();

    private MongoClientFactory() {
    }

    public static MongoClient create(MongoDBConfiguration configuration) {
        if (useX509AuthenticationIfSSLEnabledAndUserNameSet(configuration)) {
            return new MongoClient(getAddressListFrom(configuration), getMongoCredentialList(configuration),
                    getClientOptions(configuration));
        }
        return new MongoClient(getAddressListFrom(configuration), getClientOptions(configuration));
    }

    protected static List<ServerAddress> getAddressListFrom(MongoDBConfiguration configuration) {

        String hostPorts = configuration.getHostPorts();
        if (hostPorts == null || hostPorts.equals("")) {
            throw new RuntimeException("MongoDB configuration is invalid: missing serverUrls (host:port).");
        }

        List<ServerAddress> addressList = new ArrayList<>();
        for (String s : hostPorts.split(",")) {
            if (s != null && s.length() > 0) {
                String[] hostPort = s.trim().split(":");

                String host = hostPort[0];
                String port = null;

                if (hostPort.length > 1) {
                    port = hostPort[1];
                }
                if (host.trim().length() < 1) {
                    throw new RuntimeException(
                            "MongoDB configuration is invalid: missing host in serverUrls list.");
                }

                try {
                    if (port != null) {
                        addressList.add(new ServerAddress(host, Integer.parseInt(port)));
                        LOG.debug("configured mongoDb host:port={}:{}", host, port);
                    } else {
                        addressList.add(new ServerAddress(host));
                        LOG.debug("configured mongoDb host={}:defaultPort (27017)", host);
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "MongoDB configuration is invalid: invalid port number in serverUrls list.");
                }
            }
        }
        return addressList;
    }

    protected static MongoClientOptions getClientOptions(MongoDBConfiguration config) {

        MongoClientOptions.Builder builder = MongoClientOptions.builder()
                .connectionsPerHost(config.getNumberOfConnections())
                .threadsAllowedToBlockForConnectionMultiplier(config.getMaxBlockingThreads())
                .socketTimeout(config.getSocketTimeout()).connectTimeout(config.getConnectTimeout())
                .writeConcern(getWriteConcernObj(config.getWriteConcern()))
                .readPreference(getReadPreferenceObj(config.getReadPreference()))
                .maxWaitTime(config.getMaxWaitTime()).serverSelectionTimeout(config.getServerSelectionTimeout())
                .heartbeatConnectTimeout(config.getHeartbeatConnectTimeout())
                .heartbeatFrequency(config.getHeartbeatFrequency())
                .heartbeatSocketTimeout(config.getHeartbeatSocketTimeout());
        return builder.build();
    }

    protected static WriteConcern getWriteConcernObj(String writeConcern) {
        if (writeConcern != null) {
            return WriteConcern.valueOf(writeConcern);
        }
        return DEFAULT_WRITE_CONCERN;
    }

    protected static ReadPreference getReadPreferenceObj(String readPreference) {
        if (readPreference != null) {
            return ReadPreference.valueOf(readPreference);
        }
        return DEFAULT_READ_PREFERENCE;
    }

    protected static List<MongoCredential> getMongoCredentialList(MongoDBConfiguration config) {
        return Arrays.asList(MongoCredential.createMongoX509Credential(config.getUserNameX509()));
    }

    private static boolean useX509AuthenticationIfSSLEnabledAndUserNameSet(MongoDBConfiguration configuration) {
        return configuration.useSSL() && StringUtils.isNotBlank(configuration.getUserNameX509());
    }
}
