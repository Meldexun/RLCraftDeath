package meldexun.rldeath;

import java.util.Map;

import meldexun.reflectionutil.ReflectionField;
import meldexun.rldeath.config.RLDeathConfig;
import meldexun.rldeath.integration.CallableHorses;
import meldexun.rldeath.integration.ClaimIt;
import meldexun.rldeath.integration.CopygirlsWearableBackpacks;
import meldexun.rldeath.integration.LycanitesMobs;
import meldexun.rldeath.integration.Waystones;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = RLDeath.MODID, acceptableRemoteVersions = "*")
@EventBusSubscriber(modid = RLDeath.MODID)
public class RLDeath {

	public static final String MODID = "rldeath";

	private static final ReflectionField<BlockPos> SPAWN_POS = new ReflectionField<>(EntityPlayer.class,
			"field_71077_c", "spawnPos");
	private static final ReflectionField<Boolean> SPAWN_FORCED = new ReflectionField<>(EntityPlayer.class,
			"field_82248_d", "spawnForced");
	private static final ReflectionField<Map<Integer, BlockPos>> SPAWN_CHUNK_MAP = new ReflectionField<>(
			EntityPlayer.class, "spawnChunkMap", "spawnChunkMap");
	private static final ReflectionField<Map<Integer, Boolean>> SPAWN_FORCED_MAP = new ReflectionField<>(
			EntityPlayer.class, "spawnForcedMap", "spawnForcedMap");

	private static boolean waystonesLoaded;
	private static boolean lycanitesMobsLoaded;
	private static boolean claimItLoaded;
	private static boolean callableHorsesLoaded;
	private static boolean copygilsWearableBackpacksLoaded;

	@EventHandler
	public void onFMLPostInitializationEvent(FMLPostInitializationEvent event) {
		callableHorsesLoaded = Loader.isModLoaded("callablehorses");
		claimItLoaded = Loader.isModLoaded("claimit");
		copygilsWearableBackpacksLoaded = Loader.isModLoaded("wearablebackpacks");
		lycanitesMobsLoaded = Loader.isModLoaded("lycanitesmobs");
		waystonesLoaded = Loader.isModLoaded("waystones");
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onLivingDeathEvent(LivingDeathEvent event) {
		if (!(event.getEntity() instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) event.getEntity();
		if (RLDeathConfig.resetEnderChest)
			resetSpawnPoint(player);
		if (RLDeathConfig.resetSpawnPoint)
			resetEnderChestInventory(player);

		if (callableHorsesLoaded && RLDeathConfig.integration.resetCallableHorses)
			CallableHorses.resetCallableHorses(player);
		if (claimItLoaded && RLDeathConfig.integration.resetClaimIt)
			ClaimIt.resetClaimIt(player);
		if (copygilsWearableBackpacksLoaded && RLDeathConfig.integration.resetCopygirlsWearableBackpacks)
			CopygirlsWearableBackpacks.resetCopygirlsWearableBackpacks(player);
		if (lycanitesMobsLoaded && RLDeathConfig.integration.resetLycanitesMobs)
			LycanitesMobs.resetLycanitesMobs(player);
		if (waystonesLoaded && RLDeathConfig.integration.resetWaystones)
			Waystones.resetWaystones(player);
	}

	private static void resetSpawnPoint(EntityPlayer player) {
		SPAWN_POS.set(player, null);
		SPAWN_FORCED.setBoolean(player, false);
		SPAWN_CHUNK_MAP.get(player).clear();
		SPAWN_FORCED_MAP.get(player).clear();
	}

	private static void resetEnderChestInventory(EntityPlayer player) {
		player.getInventoryEnderChest().clear();
	}

}
