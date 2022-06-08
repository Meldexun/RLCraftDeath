package meldexun.rldeath.integration;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noppes.vc.VCBlocks;
import noppes.vc.blocks.tiles.TileTombstone;

public class VariedCommodities {

	public static void placeVariedCommoditiesTombstone(EntityPlayer player, DamageSource source) {
		BlockPos pos = nearestReplaceable(player.world, new BlockPos(player));
		player.world.setBlockState(pos, VCBlocks.tombstone.getDefaultState());
		((TileTombstone) player.world.getTileEntity(pos)).setText(source.getDeathMessage(player).getFormattedText());
	}

	private static BlockPos nearestReplaceable(World world, BlockPos position) {
		Set<BlockPos> checked = new HashSet<>();
		Queue<BlockPos> queue = new ArrayDeque<>();
		queue.add(position);

		while (true) {
			BlockPos pos = queue.remove();
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock().isReplaceable(world, pos)) {
				return pos;
			}
			for (EnumFacing facing : EnumFacing.VALUES) {
				BlockPos neighbor = pos.offset(facing);
				if (checked.add(neighbor)) {
					queue.add(neighbor);
				}
			}
		}
	}

}
