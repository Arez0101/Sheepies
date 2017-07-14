package net.arez0101.sheepies;

import org.cyclops.cyclopscore.config.ConfigHandler;
import org.cyclops.cyclopscore.init.IObjectReference;
import org.cyclops.cyclopscore.init.ItemCreativeTab;
import org.cyclops.cyclopscore.init.ModBaseVersionable;
import org.cyclops.cyclopscore.init.RecipeHandler;
import org.cyclops.cyclopscore.proxy.ICommonProxy;

import net.arez0101.sheepies.config.ConfigGeneral;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.MOD_VERSION,
		useMetadata = true,
		dependencies = Reference.DEPENDENCIES
)
public class Sheepies extends ModBaseVersionable {
	
	@Instance(value = Reference.MOD_ID)
	public static Sheepies INSTANCE;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static ICommonProxy PROXY;
	
	public static CreativeTabs TAB = new SheepiesTab();

	public Sheepies() {
		super(Reference.MOD_ID, Reference.MOD_NAME, Reference.MOD_VERSION);
	}
	
	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	@EventHandler
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	@EventHandler
	@Override
	public void onServerStarting(FMLServerStartingEvent event) {
		super.onServerStarting(event);
	}
	
	@EventHandler
	@Override
	public void onServerStarted(FMLServerStartedEvent event) {
		super.onServerStarted(event);
	}
	
	@EventHandler
	@Override
	public void onServerStopping(FMLServerStoppingEvent event) {
		super.onServerStopping(event);
	}
	
	@EventHandler
	@Override
	public void onMissingMappings(FMLMissingMappingsEvent event) {
		super.onMissingMappings(event);
	}

	@Override
	protected RecipeHandler constructRecipeHandler() {
		// TODO
		return null;
	}

	@Override
	public CreativeTabs constructDefaultCreativeTab() {
		return new ItemCreativeTab(this, new IObjectReference<Item>() {

			@Override
			public Item getObject() {
				return Items.SHEARS;
			}
			
		});
	}

	@Override
	public ICommonProxy getProxy() {
		return PROXY;
	}
	
	@Override
	public void onMainConfigsRegister(ConfigHandler configHandler) {
		// TODO
	}
	
	@Override
	public void onGeneralConfigsRegister(ConfigHandler configHandler) {
		configHandler.add(new ConfigGeneral());
	}
}
