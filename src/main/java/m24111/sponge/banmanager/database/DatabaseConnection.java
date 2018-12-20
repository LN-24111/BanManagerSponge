package m24111.sponge.banmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;

import lombok.Getter;
import m24111.sponge.banmanager.BanManager;

public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Logger logger = BanManager.getInstance().getLogger();
	private @Getter Connection connection;
	
	private DatabaseConnection() throws SQLException {
		DatabaseConfig config = DatabaseConfig.getInstance();
		
		connection = DriverManager.getConnection(config.getUrl(), 
													 config.getUser(),
													 config.getPassword());
	}
	
	public static DatabaseConnection getInstance() throws SQLException {
		if (instance == null)
			instance = new DatabaseConnection();
		return instance;
	}
}
