package meldexun.rldeath.config;

import meldexun.rldeath.RLDeath;
import net.minecraftforge.common.config.Config;

@Config(modid = RLDeath.MODID)
public class RLDeathConfig {

	public static Integration integration = new Integration();

	public static boolean resetEnderChest = true;
	public static boolean resetSpawnPoint = true;

	public static class Integration {

		public boolean placeVariedCommoditiesTomb = true;

		public boolean resetCallableHorses = true;
		public boolean resetClaimIt = true;
		public boolean resetCopygirlsWearableBackpacks = true;
		public boolean resetLycanitesMobs = true;
		public boolean resetWaystones = true;

	}

}
