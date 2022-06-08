package meldexun.rldeath.integration;

import java.util.ArrayList;

import net.blay09.mods.waystones.PlayerWaystoneHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class Waystones {

	public static void resetWaystones(EntityPlayer player) {
		NBTTagCompound waystoneTag = PlayerWaystoneHelper.getWaystonesTag(player);
		for (String k : new ArrayList<>(waystoneTag.getKeySet())) {
			waystoneTag.removeTag(k);
		}
	}

}
