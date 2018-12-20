package m24111.sponge.banmanager.database;

import java.io.IOException;

import lombok.Getter;
import m24111.sponge.banmanager.util.Config;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class DatabaseConfig extends Config {
	private static final String FILE_NAME = "database.conf";	
	
	private static final String URL_FIELD = "URL";
	private static final String USER_FIELD = "User";
	private static final String PASSWORD_FIELD = "Password";
	
    private @Getter String url;
    private @Getter String user;
    private @Getter String password;
	
    private static DatabaseConfig instance;
	
	public static DatabaseConfig getInstance() {
		if (instance == null)
			instance = new DatabaseConfig();
		return instance;
	}
	
	private DatabaseConfig() {
		super(FILE_NAME);
		logger.debug(path.toString());
		
		ConfigurationLoader<CommentedConfigurationNode> loader =
		        HoconConfigurationLoader.builder().setPath(path).build();
		
		try {
			CommentedConfigurationNode root = loader.load();
			if (!root.hasMapChildren()) {
				root = loader.createEmptyNode();
			    generateDefaultConfig(root);
			    loader.save(root);
			}
			else {
				url = root.getNode(URL_FIELD).getString();
			    user = root.getNode(USER_FIELD).getString();
			    password = root.getNode(PASSWORD_FIELD).getString();			
			}
			
		} catch (IOException e) {
			logger.error("Unable to load configurate. This may be due to read/write permission");
		}		
	}
	
	private void generateDefaultConfig(CommentedConfigurationNode root) {
		logger.warn("No config found. Please make sure you have set up the database");
		root.getNode(URL_FIELD);
	    root.getNode(USER_FIELD);
	    root.getNode(PASSWORD_FIELD);
	}
}
