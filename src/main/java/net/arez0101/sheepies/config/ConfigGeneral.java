package net.arez0101.sheepies.config;

import org.apache.logging.log4j.Level;
import org.cyclops.cyclopscore.config.ConfigurableProperty;
import org.cyclops.cyclopscore.config.ConfigurableType;
import org.cyclops.cyclopscore.config.ConfigurableTypeCategory;
import org.cyclops.cyclopscore.config.extendedconfig.DummyConfig;
import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.tracking.Versions;

import net.arez0101.sheepies.Reference;
import net.arez0101.sheepies.Sheepies;

public class ConfigGeneral extends DummyConfig {
	
	@ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "DO NOT CHANGE THIS VALUE", showInGui = false)
	public static String version = Reference.MOD_VERSION;
	
	@ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "Whether or not the version checker is enabled.", showInGui = false)
	public static boolean versionChecker = true;

	public ConfigGeneral() {
		super(Sheepies.INSTANCE, true, "general", null, ConfigGeneral.class);
	}
	
	public static ConfigurableType TYPE = ConfigurableType.DUMMY;
	
	@Override
	public void onRegistered() {
		if (!version.equals(Reference.MOD_VERSION)) {
			this.getMod().log(Level.WARN, "The config file for " + Reference.MOD_NAME + " is outdated. Please consider removing it so it may be recreated.");
		}
		
		if (versionChecker) {
			Versions.registerMod(this.getMod(), Sheepies.INSTANCE, Reference.VERSION_URL);
		}
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
