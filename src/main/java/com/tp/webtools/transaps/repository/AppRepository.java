package com.tp.webtools.transaps.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.tp.webtools.transaps.dao.CassandraSessionFactory;
import com.tp.webtools.transaps.model.App;

@Repository
public class AppRepository {
 
	public static final Logger LOGGER = LoggerFactory.getLogger(AppRepository.class);
	
	@Autowired
	private CassandraSessionFactory cassandraSessionFacotry;
	
	private MappingManager manager;
	
	private Mapper<App> mapper;
	
    private Session session;

    public AppRepository() {
        this.session = cassandraSessionFacotry.getCassandraSession();
        this.manager = new MappingManager(session);
        this.mapper = manager.mapper(App.class);
    }

    /**
     * Create keyspace in cassandra DB
     */
    public void createKeyspace(String keyspaceName) {
        final String query = "CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 1 }";
        session.execute(query);
        LOGGER.info("Created keyspace: " + "'" + keyspaceName + "'");
    }

    /**
     * Create app table in cassandra DB
     */
    public void createTable(String keyspaceName, String talbeName) {
        final String query = "CREATE TABLE IF NOT EXISTS "+ keyspaceName + "." + talbeName + " ("
        		+ "id int PRIMARY KEY,"
        		+ "title text,"
        		+ "description text,"
        		+ "content text,"
        		+ "author text,"
        		+ "division text,"
        		+ "downloads int,"
        		+ "rate int,"
        		+ "creationTime timestamp,"
        		+ "lastUpdateTime timestamp,"
        		+ "purpose text,"
        		+ "languages list<text>,"
        		+ "sourceFileTypes list<text>,"
        		+ "appTypes list<text>)";
        session.execute(query);
        LOGGER.info("Created table: " + "'" + keyspaceName + "." + talbeName + "'");
    }

    /**
     * Select all rows from app table
     */
    public Result<App> selectAllApps(String keyspaceName, String talbeName) {

        final String query = "SELECT * FROM "+ keyspaceName + "." + talbeName;
        ResultSet results = session.execute(query);

        for (Row row : results.all()) {
            LOGGER.info("Obtained row: {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} ", 
            		row.getInt("id"),
            		row.getString("title"),
            		row.getString("description"),
            		row.getString("content"),
            		row.getString("author"),
            		row.getString("division"),
            		row.getInt("downloads"),
            		row.getInt("date"),
            		row.getTimestamp("creationTime"),
            		row.getTimestamp("lastUpdateTime"),
            		row.getString("purpose"),
            		row.getList("languages", String.class),
            		row.getList("sourceFileTypes", String.class),
            		row.getList("appTypes", String.class));
        }
        
        Result<App> apps = mapper.map(results);
        return apps;
    }

    /**
     * Select a row from app table
     *
     * @param id
     */
    public App selectAppById(int id, String keyspaceName, String talbeName) {
        final String query = "SELECT * FROM "+ keyspaceName + "." + talbeName + " where id = " + id;
        ResultSet results = session.execute(query);
        Row row = results.one();

        LOGGER.info("Obtained row: {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} ", 
        		row.getInt("id"),
        		row.getString("title"),
        		row.getString("description"),
        		row.getString("content"),
        		row.getString("author"),
        		row.getString("division"),
        		row.getInt("downloads"),
        		row.getInt("date"),
        		row.getTimestamp("creationTime"),
        		row.getTimestamp("lastUpdateTime"),
        		row.getString("purpose"),
        		row.getList("languages", String.class),
        		row.getList("sourceFileTypes", String.class),
        		row.getList("appTypes", String.class));
        
        App app = mapper.map(results).one();
        return app;
    }
    
    /**
     * Select a row from app table
     *
     * @param title
     */
    public App selectAppByTitle(String title, String keyspaceName, String talbeName) {
        final String query = "SELECT * FROM "+ keyspaceName + "." + talbeName + " where title = " + title;
        ResultSet results = session.execute(query);
        Row row = results.one();

        LOGGER.info("Obtained row: {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} ", 
        		row.getInt("id"),
        		row.getString("title"),
        		row.getString("description"),
        		row.getString("content"),
        		row.getString("author"),
        		row.getString("division"),
        		row.getInt("downloads"),
        		row.getInt("date"),
        		row.getTimestamp("creationTime"),
        		row.getTimestamp("lastUpdateTime"),
        		row.getString("purpose"),
        		row.getList("languages", String.class),
        		row.getList("sourceFileTypes", String.class),
        		row.getList("appTypes", String.class));
        
        App app = mapper.map(results).one();
        return app;
    }

    /**
     * Delete app table.
     */
    public void deleteTable(String keyspaceName, String talbeName) {
        final String query = "DROP TABLE IF EXISTS "+ keyspaceName + "." + talbeName;
        session.execute(query);
        LOGGER.info("Deleted table: " + "'" + keyspaceName + "." + talbeName + "'");
    }

    /**
     * Insert a row into app table
     */
    public void insertApp(PreparedStatement statement, App app) {
        BoundStatement boundStatement = new BoundStatement(statement);
        final String query = boundStatement.bind(
        		app.getId(),
        		app.getTitle(),
        		app.getDescription(),
        		app.getContent(),
        		app.getAuthor(),
        		app.getDivision(),
        		app.getDownloads(),
        		app.getRate(),
        		app.getCreationTime(),
        		app.getLastUpdateTime(),
        		app.getPurposes(),
        		app.getLanguages(),
        		app.getSourceFileTypes(),
        		app.getAppTypes()).toString();
        
        session.execute(query);
        LOGGER.info("Inserted row: " + app.toString());
    }

    /**
     * Create a PrepareStatement to insert a row to app table
     *
     * @return PreparedStatement
     */
    public PreparedStatement prepareInsertStatement(String keyspaceName, String talbeName) {
        final String insertStatement = "INSERT INTO "+ keyspaceName + "." + talbeName + " ("
        		+ "id,"
        		+ "title,"
        		+ "description,"
        		+ "content,"
        		+ "author,"
        		+ "division,"
        		+ "downloads,"
        		+ "rate,"
        		+ "creationTime,"
        		+ "lastUpdateTime,"
        		+ "purpose,"
        		+ "languages,"
        		+ "sourceFileTypes,"
        		+ "appTypes"
        		+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return session.prepare(insertStatement);
    }
}
