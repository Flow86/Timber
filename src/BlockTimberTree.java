package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;

public class BlockTimberTree extends BlockLog {
  public static boolean isAxe;
	
  public BlockTimberTree(int i) {
    super(i);
    blockIndexInTexture = 20;
  }

  public void onBlockRemoval(World world, int i, int j, int k) { 
	  Minecraft mc = ModLoader.getMinecraftInstance();
	  if (isAxe && !Keyboard.isKeyDown(mod_Timber.key) && !mc.isMultiplayerWorld())
      check(world, i, j, k);
        
    byte byte0 = 4;
    int l = byte0 + 1;
    if (world.checkChunksExist(i - l, j - l, k - l, i + l, j + l, k + l)) {
      for (int i1 = -byte0; i1 <= byte0; i1++) {
        for (int j1 = -byte0; j1 <= byte0; j1++) {
          for (int k1 = -byte0; k1 <= byte0; k1++) {
            int l1 = world.getBlockId(i + i1, j + j1, k + k1);
            
            if (l1 != Block.leaves.blockID)
              continue;
            
            int i2 = world.getBlockMetadata(i + i1, j + j1, k + k1);
            if ((i2 & 8) == 0)
              world.setBlockMetadataWithNotify(i + i1, j + j1, k + k1, i2 | 8);
          }
        }
      }
    }
  }

  private void check(World world, int i, int j, int k) {
    int l = 1;
    for (int i1 = -l; i1 <= l; i1++) {
      for (int j1 = -l; j1 <= l; j1++) {
        for (int k1 = 0; k1 <= l; k1++) {
          int l1 = world.getBlockId(i + i1, j + k1, k + j1);
            
          if (l1 != Block.wood.blockID)
            continue;
                    
          Block block = Block.blocksList[world.getBlockId(i + i1, j + k1, k + j1)];
          int i2 = world.getBlockMetadata(i + i1, j + k1, k + j1);
            
          if (block != null && world.setBlockWithNotify(i + i1, j + k1, k + j1, 0))
            block.dropBlockAsItem(world, i + i1, j + k1, k + j1, i2, 0);
        }
      }
    }
  }

  public static void setAxe(Boolean bool) {
    isAxe = bool.booleanValue();
  }
}
