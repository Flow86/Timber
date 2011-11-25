// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            BlockLog, World, Block, BlockLeaves

public class BlockTree extends BlockLog
{

    protected BlockTree(int i)
    {
        super(i);
        blockIndexInTexture = 20;
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        if(isAxe)
        {
            check(world, i, j, k);
        }
        byte byte0 = 4;
        int l = byte0 + 1;
        if(world.checkChunksExist(i - l, j - l, k - l, i + l, j + l, k + l))
        {
            for(int i1 = -byte0; i1 <= byte0; i1++)
            {
                for(int j1 = -byte0; j1 <= byte0; j1++)
                {
                    for(int k1 = -byte0; k1 <= byte0; k1++)
                    {
                        int l1 = world.getBlockId(i + i1, j + j1, k + k1);
                        if(l1 != Block.leaves.blockID)
                        {
                            continue;
                        }
                        int i2 = world.getBlockMetadata(i + i1, j + j1, k + k1);
                        if((i2 & 8) == 0)
                        {
                            world.setBlockMetadataWithNotify(i + i1, j + j1, k + k1, i2 | 8);
                        }
                    }

                }

            }

        }
    }

    private void check(World world, int i, int j, int k)
    {
        int l = 1;
        for(int i1 = -l; i1 <= l; i1++)
        {
            for(int j1 = -l; j1 <= l; j1++)
            {
                for(int k1 = 0; k1 <= l; k1++)
                {
                    int l1 = world.getBlockId(i + i1, j + k1, k + j1);
                    if(l1 != Block.wood.blockID)
                    {
                        continue;
                    }
                    Block block = Block.blocksList[world.getBlockId(i + i1, j + k1, k + j1)];
                    int i2 = world.getBlockMetadata(i + i1, j + k1, k + j1);
                    if(block != null && world.setBlockWithNotify(i + i1, j + k1, k + j1, 0))
                    {
                        block.dropBlockAsItem(world, i + i1, j + k1, k + j1, i2, 0);
                    }
                }

            }

        }

    }

    public static void setAxe(Boolean boolean1)
    {
        isAxe = boolean1.booleanValue();
    }

    public static boolean isAxe;
}
