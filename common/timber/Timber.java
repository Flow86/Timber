package timber;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "Timber!", name = "Timber!", version = "@TIMBER_VERSION@", dependencies = "required-after:Forge")
public class Timber {
	@Instance("Timber!")
	public static Timber instance;

	public static Configuration timberConfiguration;
	public static Logger timberLog = Logger.getLogger("Timber!");

	public static BlockTree blockTree = null;

	private static int[] axeIds = null;

	@PreInit
	public void preInitialize(FMLPreInitializationEvent evt) {

		timberLog.setParent(FMLLog.getLogger());
		timberLog.info("Starting Timber #@BUILD_NUMBER@ @TIMBER_VERSION@ (Built for Minecraft @MINECRAFT_VERSION@ with Forge @FORGE_VERSION@");
		timberLog.info("Copyright (c) Flow86, 2013");

		timberConfiguration = new Configuration(new File(evt.getModConfigurationDirectory(), "timber.cfg"));
		try {
			timberConfiguration.load();

			Property axeIdsProp = timberConfiguration.get(Configuration.CATEGORY_GENERAL, "AxeIds", new int[] { 258, 271, 275, 279, 286 });
			axeIds = axeIdsProp.getIntList();
		} finally {
			if (timberConfiguration.hasChanged())
				timberConfiguration.save();
		}
	}

	@Init
	public void load(FMLInitializationEvent evt) {
		Block.blocksList[Block.wood.blockID] = null;

		blockTree = new BlockTree(Block.wood.blockID);

		Item.itemsList[Block.wood.blockID] = null;
		Item.itemsList[Block.wood.blockID] = new ItemMultiTextureTile(256-Block.wood.blockID, blockTree, BlockLog.woodType).setUnlocalizedName("log");
	}

	public static boolean isItemAxe(EntityPlayer thePlayer) {
		if (thePlayer == null || thePlayer.getCurrentEquippedItem() == null)
			return false;

		int itemID = thePlayer.getCurrentEquippedItem().itemID;

		for (int axeId : axeIds) {
			if (itemID == axeId)
				return true;
		}

		return false;
	}
}
