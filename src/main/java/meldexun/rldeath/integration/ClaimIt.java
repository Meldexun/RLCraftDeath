package meldexun.rldeath.integration;

import dev.itsmeow.claimit.api.claim.ClaimManager;
import net.minecraft.entity.player.EntityPlayer;

public class ClaimIt {

	public static void resetClaimIt(EntityPlayer player) {
		ClaimManager claimManager = ClaimManager.getManager();
		claimManager.getClaimsOwnedByPlayer(player.getGameProfile().getId()).forEach(claimManager::deleteClaim);
	}

}
