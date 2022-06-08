package meldexun.rldeath.integration;

import java.util.ArrayList;

import com.lycanitesmobs.core.entity.ExtendedPlayer;
import com.lycanitesmobs.core.info.Beastiary;
import com.lycanitesmobs.core.pets.PetEntry;

import net.minecraft.entity.player.EntityPlayer;

public class LycanitesMobs {

	public static void resetLycanitesMobs(EntityPlayer player) {
		ExtendedPlayer lmPlayerData = ExtendedPlayer.getForPlayer(player);

		Beastiary beastiary = lmPlayerData.getBeastiary();
		beastiary.creatureKnowledgeList.clear();
		beastiary.sendAllToClient();

		for (PetEntry pet : new ArrayList<>(lmPlayerData.petManager.entries.values())) {
			pet.remove();
		}
	}

}
