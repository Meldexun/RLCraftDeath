package meldexun.rldeath.integration;

import net.mcft.copy.backpacks.api.BackpackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CopygirlsWearableBackpacks {

	public static void resetCopygirlsWearableBackpacks(EntityPlayer player) {
		BackpackHelper.setEquippedBackpack(player, ItemStack.EMPTY, null);
	}

}
