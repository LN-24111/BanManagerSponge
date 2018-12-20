package m24111.sponge.banmanager;

import java.nio.file.Path;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import lombok.Getter;
import m24111.sponge.banmanager.database.DatabaseConnection;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id="banmanager", name="BanManager", version="1.0")
public class BanManager {
	private static @Getter BanManager instance;
	
	@Inject
	private @Getter Logger logger;
	
	@Inject
	@DefaultConfig(sharedRoot = false)
	private @Getter Path defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = false)
	private @Getter ConfigurationLoader<CommentedConfigurationNode> configManager;

	@Inject
	@ConfigDir(sharedRoot = false)
	private @Getter Path privateConfigDir;
	
	boolean disabled = false;
	

    @Listener
    public void onPreInitialization(GamePreInitializationEvent event) {
    	instance = this;
    	logger.debug("Plugin loaded");
    	if (!setupDatabase()) {    		
    		logger.error("Failed to initialize database connection. Disabling plugin");
    		cleanupDatabase();
    	}
    	//TODO
    	setupCommands();
    }
    
    @Listener
    public void onGameStopping(GameStoppingEvent event) {
    	logger.debug("Plugin exited");
    	cleanupDatabase();
   
    }
    
	private void cleanupDatabase() {
		// TODO Auto-generated method stub
		
	}




	private void setupCommands() {
		// TODO
		
	}




	private boolean setupDatabase() {
		try {
			DatabaseConnection.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
