package meldexun.rldeath.integration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import tschipp.callablehorses.common.HorseManager;
import tschipp.callablehorses.common.capabilities.horseowner.IHorseOwner;
import tschipp.callablehorses.common.helper.HorseHelper;

public class CallableHorses {

	public static void resetCallableHorses(EntityPlayer player) {
		IHorseOwner horseOwner = HorseHelper.getOwnerCap(player);
		String ownedID = horseOwner.getStorageUUID();
		if (!ownedID.isEmpty()) {
			Entity ent = HorseManager.findHorseWithStorageID(ownedID, player.world);
			if (ent != null) {
				HorseManager.clearHorse(HorseHelper.getHorseCap(ent));
			} else {
				HorseHelper.getWorldData(player.world).disbandHorse(ownedID);
			}
		}
		horseOwner.clearHorse();
	}

}
