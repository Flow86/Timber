package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;

public class mod_Timber extends BaseMod {
  public String getVersion() { return "1.0.0"; }
  
  @MLProp (info="Sets the key to hold down to toggle Timber! off. (example, 46 = C)") 
    public static int key = Keyboard.KEY_C;
  
  static Block tree;
    
  public void load() {
    Block.blocksList[17] = null;
    tree = new BlockTimberTree(17).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setBlockName("log");
    ModLoader.SetInGameHook(this, true, true);
  }
    
  public boolean OnTickInGame(float f, Minecraft minecraft) {
    ItemStack itemstack = minecraft.thePlayer.getCurrentEquippedItem();
    
    if (itemstack != null && itemstack.getItem() instanceof ItemAxe) {
      BlockTimberTree.setAxe(Boolean.valueOf(true));
    } else {
      BlockTimberTree.setAxe(Boolean.valueOf(false));
    }
	
    try {
      Class.forName("ItemPlasticAxe");
      if (itemstack != null && itemstack.getItem() instanceof ItemPlasticAxe) {
        BlockTimberTree.setAxe(Boolean.valueOf(true));
      } else {
        BlockTimberTree.setAxe(Boolean.valueOf(false));
      }
    } catch (ClassNotFoundException err) {}
	
    return true;
  }
}