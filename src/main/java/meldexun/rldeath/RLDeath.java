package meldexun.rldeath;

import java.util.ArrayList;
import java.util.Map;

import com.lycanitesmobs.core.entity.ExtendedPlayer;
import com.lycanitesmobs.core.info.Beastiary;
import com.lycanitesmobs.core.pets.PetEntry;

import dev.itsmeow.claimit.api.claim.ClaimManager;
import meldexun.reflectionutil.ReflectionField;
import net.blay09.mods.waystones.PlayerWaystoneHelper;
import net.mcft.copy.backpacks.api.BackpackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tschipp.callablehorses.common.helper.HorseHelper;

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

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void init(LivingDeathEvent event) {
		if (!(event.getEntity() instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) event.getEntity();
		resetSpawnPoint(player);
		resetEnderChestInventory(player);
		resetWaystones(player);
		resetLycanitesMobs(player);
		resetClaimIt(player);
		resetCallableHorse(player);
		resetCopygirlBackpack(player);
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

	private static void resetWaystones(EntityPlayer player) {
		NBTTagCompound waystoneTag = PlayerWaystoneHelper.getWaystonesTag(player);
		for (String k : new ArrayList<>(waystoneTag.getKeySet())) {
			waystoneTag.removeTag(k);
		}
	}

	private static void resetLycanitesMobs(EntityPlayer player) {
		ExtendedPlayer lmPlayerData = ExtendedPlayer.getForPlayer(player);

		Beastiary beastiary = lmPlayerData.getBeastiary();
		beastiary.creatureKnowledgeList.clear();
		beastiary.sendAllToClient();

		for (PetEntry pet : new ArrayList<>(lmPlayerData.petManager.entries.values())) {
			pet.remove();
		}
	}

	private static void resetClaimIt(EntityPlayer player) {
		ClaimManager claimManager = ClaimManager.getManager();
		claimManager.getClaimsOwnedByPlayer(player.getGameProfile().getId()).forEach(claimManager::deleteClaim);
	}

	private static void resetCallableHorse(EntityPlayer player) {
		HorseHelper.getOwnerCap(player).clearHorse();
	}

	private static void resetCopygirlBackpack(EntityPlayer player) {
		BackpackHelper.setEquippedBackpack(player, ItemStack.EMPTY, null);
	}

}
