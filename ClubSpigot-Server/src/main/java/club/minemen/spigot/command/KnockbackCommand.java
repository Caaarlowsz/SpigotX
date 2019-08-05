package club.minemen.spigot.command;

import club.minemen.spigot.ClubSpigot;

import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class KnockbackCommand extends Command {
	public KnockbackCommand() {
		super("knockback");
		this.setAliases(Arrays.asList("kb"));
		this.setUsage(ChatColor.RED + "Usage: /knockback <horizontal multiplier> <vertical multiplier>");
	}

	@Override
	public boolean execute(CommandSender sender, String alias, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("Unknown command. Type \"help\" for help.");
			return true;
		}
		if (args.length > 1 && NumberUtils.isNumber(args[0]) && NumberUtils.isNumber(args[1])) {
			double knockbackHorizontal = Double.parseDouble(args[0]);
			double knockbackVertical = Double.parseDouble(args[1]);

			ClubSpigot.INSTANCE.getConfig().setKnockbackHorizontal(knockbackHorizontal);
			ClubSpigot.INSTANCE.getConfig().setKnockbackVertical(knockbackVertical);
			sender.sendMessage(ChatColor.GREEN + "Set knockback values to " + ChatColor.GREEN + knockbackHorizontal + ChatColor.GREEN + " and " + ChatColor.GREEN + knockbackVertical + ChatColor.GREEN + ".");

		} else {
			sender.sendMessage(usageMessage);
			sender.sendMessage(ChatColor.GREEN + "Current knockback values: " + ClubSpigot.INSTANCE.getConfig().getKnockbackHorizontal() + " and " + ClubSpigot.INSTANCE.getConfig().getKnockbackVertical());
		}
		return true;
	}
}
