package com.tp.webtools.transaps.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.JdkSSLOptions;
import com.datastax.driver.core.RemoteEndpointAwareJdkSSLOptions;
import com.datastax.driver.core.Session;


@Component
public class CassandraSessionFactory {
	
	public static final Logger logger = LoggerFactory.getLogger(CassandraSessionFactory.class);

	@Value("${datasource.transaps.azure.cosmosdb.cassandra.host}")
    private String host;

    @Value("${datasource.transaps.azure.cosmosdb.cassandra.port}")
    private int port;
    
    @Value("${datasource.transaps.azure.cosmosdb.cassandra.username}")
    private String username;
    
    @Value("${datasource.transaps.azure.cosmosdb.cassandra.password}")
    private String password;
    
    @Value("${datasource.transaps.azure.cosmosdb.cassandra.ssl_keystore_file_path}")
    private String ssl_keystore_file_path;
    
    @Value("${datasource.transaps.azure.cosmosdb.cassandra.ssl_keystore_password}")
    private String ssl_keystore_password;
    
    @Value("${datasource.transaps.azure.cosmosdb.cassandra.keyspace_name}")
    private String keyspaceName;
    
    private File sslKeyStoreFile;
        
    private Cluster cluster;
        
    public CassandraSessionFactory() {
    	System.out.println("CassandraSessionFactory constructor loaded");
    }
    
    @PostConstruct
    public void init() {
    	System.out.println("CassandraSessionFactory configuration loaded");
    	loadConfiguration();
    	Session session = getCassandraSession();
    	createKeyspace(session);
    	createAppTable("app", session);
    }
    
    
    @PreDestroy
    public void destroy() {
        try {
            this.cluster.close();
        } catch (Exception e) {
        	logger.error("Failed to clean up Cassandra session", e);
        }
    }
    
    
    private void loadConfiguration() {
    	
    	try {
    		
    		if (ssl_keystore_file_path == null || ssl_keystore_file_path.isEmpty()) {
        		
                String javaHomeDirectory = System.getenv("JAVA_HOME");
                if (javaHomeDirectory == null || javaHomeDirectory.isEmpty()) {
                    throw new Exception("JAVA_HOME not set");
                }
                ssl_keystore_file_path = new StringBuilder(javaHomeDirectory).append("/jre/lib/security/cacerts").toString();
            }

        	ssl_keystore_password = (ssl_keystore_password != null && !ssl_keystore_password.isEmpty()) ?
        			ssl_keystore_password : "changeit";

            sslKeyStoreFile = new File(ssl_keystore_file_path);

            if (!sslKeyStoreFile.exists() || !sslKeyStoreFile.canRead()) {
                throw new Exception(String.format("Unable to access the SSL Key Store file from %s", ssl_keystore_file_path));
            }
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    }
    
    
    public Session getCassandraSession() {

    	try {

            final KeyStore keyStore = KeyStore.getInstance("JKS");
            try (final InputStream is = new FileInputStream(sslKeyStoreFile)) {
                keyStore.load(is, ssl_keystore_password.toCharArray());
            }

            final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, ssl_keystore_password.toCharArray());
            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);

            // Creates a socket factory for HttpsURLConnection using JKS contents.
            final SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new java.security.SecureRandom());

            JdkSSLOptions sslOptions = RemoteEndpointAwareJdkSSLOptions.builder()
                    .withSSLContext(sc)
                    .build();
            cluster = Cluster.builder()
                    .addContactPoint(host)
                    .withPort(port)
                    .withCredentials(username, password)
                    .withSSL(sslOptions)
                    .build();

            return cluster.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	
        return null;
    }

    
    /**
     * Create keyspace in cassandra DB
     */
    private void createKeyspace(Session session) {
        final String query = "CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 1 }";
        session.execute(query);
        logger.info("Created keyspace: " + "'" + keyspaceName + "'");
    }
    
    /**
     * Create app table in cassandra DB
     */
    private void createAppTable(String talbeName, Session session) {
        final String query = "CREATE TABLE IF NOT EXISTS "+ keyspaceName + "." + talbeName + " ("
        		+ "id int PRIMARY KEY,"
        		+ "profile_picture text,"
        		+ "title text,"
        		+ "description text,"
        		+ "content text,"
        		+ "author text,"
        		+ "division text,"
        		+ "downloads int,"
        		+ "rate int,"
        		+ "creation_time timestamp,"
        		+ "last_update_time timestamp,"
        		+ "purposes text,"
        		+ "languages list<text>,"
        		+ "source_file_types list<text>,"
        		+ "app_types list<text>)";
        session.execute(query);
        logger.info("Created table: " + "'" + keyspaceName + "." + talbeName + "'");
    }
}
