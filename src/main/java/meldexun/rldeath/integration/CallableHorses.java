package meldexun.rldeath.integration;

import net.minecraft.entity.player.EntityPlayer;
import tschipp.callablehorses.common.helper.HorseHelper;

public class CallableHorses {

	public static void resetCallableHorses(EntityPlayer player) {
		HorseHelper.getOwnerCap(player).clearHorse();
	}

}
