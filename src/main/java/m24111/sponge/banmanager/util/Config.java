package m24111.sponge.banmanager.util;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;

import com.google.inject.Inject;

import m24111.sponge.banmanager.BanManager;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public abstract class Config {
	protected BanManager plugin = BanManager.getInstance();
	protected Logger logger = BanManager.getInstance().getLogger();
	
	private Path defaultConfig = plugin.getDefaultConfig();
	private Path privateConfigDir = plugin.getPrivateConfigDir();
	private ConfigurationLoader<CommentedConfigurationNode> configManager = plugin.getConfigManager();
	
	protected Path path;
	
	protected Config(String conf) {
		path = privateConfigDir.resolve(conf);
	}
	
	protected Config() {
		path = defaultConfig;
	}
}
