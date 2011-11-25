// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            BaseMod, Block, BlockTree, ModLoader, 
//            EntityPlayerSP, ItemStack, ItemAxe

public class mod_Timber extends BaseMod
{

    public String Version()
    {
        return "r4 (for b1.8.1)";
    }

    public mod_Timber()
    {
        Block.blocksList[17] = null;
        Tree = (new BlockTree(17)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setBlockName("log");
        ModLoader.SetInGameHook(this, true, true);
    }

    public boolean OnTickInGame(Minecraft minecraft)
    {
        ItemStack itemstack = minecraft.thePlayer.getCurrentEquippedItem();
        if(itemstack != null && (itemstack.getItem() instanceof ItemAxe))
        {
            float f = itemstack.getStrVsBlock(Block.planks);
            BlockTree.setAxe(Boolean.valueOf(true));
            Tree.setHardness(2.0F / f);
        } else
        {
            BlockTree.setAxe(Boolean.valueOf(false));
            Tree.setHardness(2.0F);
        }
        return true;
    }

    static Block Tree;
}
