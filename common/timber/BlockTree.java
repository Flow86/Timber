package timber;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockTree extends BlockLog {
	public static boolean isAxe;

	public static void setIsAxe(boolean axe) {
		isAxe = axe;
	}

	public BlockTree(int i) {
		super(i);
		setHardness(2.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName("log");
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int i, int j, int k) {
		if (Timber.isItemAxe(player) && !player.isSneaking())
			check(world, player, i, j, k);

		byte treeSize = 4;
		int chunkSize = treeSize + 1;

		if (world.checkChunksExist(i - chunkSize, j - chunkSize, k - chunkSize, i + chunkSize, j + chunkSize, k + chunkSize)) {
			for (int x = -treeSize; x <= treeSize; x++) {
				for (int y = -treeSize; y <= treeSize; y++) {
					for (int z = -treeSize; z <= treeSize; z++) {
						int blockId = world.getBlockId(i + x, j + y, k + z);

						if (blockId != Block.leaves.blockID)
							continue;

						int metadata = world.getBlockMetadata(i + x, j + y, k + z);

						if ((metadata & 0x8) == 0)
							world.setBlockMetadataWithNotify(i + x, j + y, k + z, metadata | 0x8, 2);
					}
				}
			}
		}

		return super.removeBlockByPlayer(world, player, i, j, k);
	}

	private void check(World world, EntityPlayer player, int i, int j, int k) {
		int treeSize = 1;

		for (int x = -treeSize; x <= treeSize; x++) {
			for (int z = -treeSize; z <= treeSize; z++) {
				for (int y = 0; y <= treeSize; y++) {
					int blockID = world.getBlockId(i + x, j + y, k + z);

					if (blockID != Block.wood.blockID)
						continue;

					Block block = Block.blocksList[world.getBlockId(i + x, j + y, k + z)];

					int metadata = world.getBlockMetadata(i + x, j + y, k + z);

					if (block == null || !world.setBlock(i + x, j + y, k + z, 0, 0, 2))
						continue;

					block.dropBlockAsItem(world, i + x, j + y, k + z, metadata, 0);
					removeBlockByPlayer(world, player, i + x, j + y, k + z);
				}
			}
		}
	}
}