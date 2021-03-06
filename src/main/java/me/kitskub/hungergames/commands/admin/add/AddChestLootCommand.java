package me.kitskub.hungergames.commands.admin.add;

import me.kitskub.hungergames.Defaults.Commands;
import me.kitskub.hungergames.Defaults.Perm;
import me.kitskub.hungergames.ItemConfig;
import me.kitskub.hungergames.HungerGames;
import me.kitskub.hungergames.commands.PlayerCommand;
import me.kitskub.hungergames.utils.ChatUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AddChestLootCommand extends PlayerCommand {

	public AddChestLootCommand() {
		super(Perm.ADMIN_ADD_CHEST_LOOT, Commands.ADMIN_ADD_HELP.getCommand(), "chestloot");
	}

	@Override
	public void handlePlayer(Player player, String label, String[] args) {
		if (args.length < 1) {
			ChatUtils.helpCommand(player, getUsage(), HungerGames.CMD_ADMIN);
			return;
		}
		float chance = 0;
		try {
			chance = Float.valueOf(args[0]);
		}
		catch (NumberFormatException e) {
			ChatUtils.error(player,"{0} is not a valid number", args[0]);
		}
		if (player.getItemInHand() == null) {
			ChatUtils.error(player,"There is no item in hand. Perhaps you used the command wrong?");
			return;
		}		
		if (args.length < 2) {

			ItemConfig.addChestLoot(null, player.getItemInHand(), chance);
		}
		else {
			ItemConfig.addChestLoot(args[1], player.getItemInHand(), chance);
		}
		ChatUtils.send(player, ChatColor.GREEN, "Item in hand added to chest loot");
	}

	@Override
	public String getInfo() {
		return "adds the itemstack in hand to the specified itemset or global if no itemset is specified";
	}

	@Override
	protected String getPrivateUsage() {
		return "chestloot <chance> [itemset]";
	}
	
}
