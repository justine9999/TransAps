package com.tp.webtools.transaps.repository;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Value("${datasource.transaps.azure.cosmosdb.cassandra.keyspace_name}")
    private String keyspaceName;
    
    private final static String talbeName = "APP";
    
    @PostConstruct
    public void init() {
    	this.session = cassandraSessionFacotry.getCassandraSession();
        this.manager = new MappingManager(this.session);
        this.mapper = manager.mapper(App.class);
    }

    /**
     * Select all rows from app table
     */
    public Result<App> selectAllApps() {

        final String query = "SELECT * FROM "+ keyspaceName + "." + talbeName;
        ResultSet results = session.execute(query);

        for (Row row : results.all()) {
            LOGGER.info("Obtained row: {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} ", 
            		row.getInt("id"),
            		row.getString("profile_picture"),
            		row.getString("title"),
            		row.getString("description"),
            		row.getString("content"),
            		row.getString("author"),
            		row.getString("division"),
            		row.getInt("downloads"),
            		row.getInt("date"),
            		row.getTimestamp("creation_time"),
            		row.getTimestamp("lastUpdate_time"),
            		row.getString("purposes"),
            		row.getList("languages", String.class),
            		row.getList("source_file_types", String.class),
            		row.getList("app_types", String.class));
        }
        
        Result<App> apps = mapper.map(results);
        return apps;
    }

    /**
     * Select a row from app table
     *
     * @param id
     */
    public App selectAppById(int id) {
        final String query = "SELECT * FROM "+ keyspaceName + "." + talbeName + " where id = " + id;
        ResultSet results = session.execute(query);
        Row row = results.one();

        LOGGER.info("Obtained row: {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} ", 
        		row.getInt("id"),
        		row.getString("profile_picture"),
        		row.getString("title"),
        		row.getString("description"),
        		row.getString("content"),
        		row.getString("author"),
        		row.getString("division"),
        		row.getInt("downloads"),
        		row.getInt("date"),
        		row.getTimestamp("creation_time"),
        		row.getTimestamp("lastUpdate_time"),
        		row.getString("purposes"),
        		row.getList("languages", String.class),
        		row.getList("source_file_types", String.class),
        		row.getList("app_types", String.class));
        
        App app = mapper.map(results).one();
        return app;
    }
    
    /**
     * Select a row from app table
     *
     * @param title
     */
    public App selectAppByTitle(String title) {
        final String query = "SELECT * FROM "+ keyspaceName + "." + talbeName + " where title = " + title;
        ResultSet results = session.execute(query);
        Row row = results.one();

        LOGGER.info("Obtained row: {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} | {} ", 
        		row.getInt("id"),
        		row.getString("profile_picture"),
        		row.getString("title"),
        		row.getString("description"),
        		row.getString("content"),
        		row.getString("author"),
        		row.getString("division"),
        		row.getInt("downloads"),
        		row.getInt("date"),
        		row.getTimestamp("creation_time"),
        		row.getTimestamp("lastUpdate_time"),
        		row.getString("purposes"),
        		row.getList("languages", String.class),
        		row.getList("source_file_types", String.class),
        		row.getList("app_types", String.class));
        
        App app = mapper.map(results).one();
        return app;
    }

    /**
     * Delete app table.
     */
    public void deleteTable() {
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
        		app.getProfile_picture(),
        		app.getTitle(),
        		app.getDescription(),
        		app.getContent(),
        		app.getAuthor(),
        		app.getDivision(),
        		app.getDownloads(),
        		app.getRate(),
        		app.getCreation_time(),
        		app.getLastUpdate_time(),
        		app.getPurposes(),
        		app.getLanguages(),
        		app.getSource_file_types(),
        		app.getApp_types()).toString();
        
        session.execute(query);
        LOGGER.info("Inserted row: " + app.toString());
    }

    /**
     * Create a PrepareStatement to insert a row to app table
     *
     * @return PreparedStatement
     */
    public PreparedStatement prepareInsertStatement() {
        final String insertStatement = "INSERT INTO "+ keyspaceName + "." + talbeName + " ("
        		+ "id,"
        		+ "profile_picture,"
        		+ "title,"
        		+ "description,"
        		+ "content,"
        		+ "author,"
        		+ "division,"
        		+ "downloads,"
        		+ "rate,"
        		+ "creation_time,"
        		+ "lastUpdate_time,"
        		+ "purposes,"
        		+ "languages,"
        		+ "source_file_types,"
        		+ "app_types"
        		+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return session.prepare(insertStatement);
    }
}
