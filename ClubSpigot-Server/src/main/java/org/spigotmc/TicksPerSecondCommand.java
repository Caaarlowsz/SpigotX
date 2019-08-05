package org.spigotmc;

import club.minemen.spigot.ClubSpigot;
import club.minemen.spigot.util.DateUtil;
import java.lang.management.ManagementFactory;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TicksPerSecondCommand extends Command {

	public TicksPerSecondCommand(String name) {
		super(name);
		this.description = "Gets the current ticks per second for the server";
		this.usageMessage = "/tps";
		this.setPermission("bukkit.command.tps");
	}

	private static String format(double tps) // PaperSpigot - made static
	{
		return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
		       + ((tps > 20.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
	}

	@Override
	public boolean execute(CommandSender sender, String currentAlias, String[] args) {
		if (!testPermission(sender)) {
			return true;
		}

		// PaperSpigot start - Further improve tick handling
		double[] tps = org.bukkit.Bukkit.spigot().getTPS();
		String[] tpsAvg = new String[tps.length];
		for (int i = 0; i < tps.length; i++) {
			tpsAvg[i] = format(tps[i]);
		}

		int totalEntities = 0;
		for (World world : Bukkit.getServer().getWorlds()) {
			totalEntities += world.getEntities().size();
		}

		sender.sendMessage(
				ClubSpigot.PRIMARY_COLOR + "TPS (1m, 5m, 15m): " +
				org.apache.commons.lang.StringUtils.join(tpsAvg, ", "));
		sender.sendMessage(
				ClubSpigot.PRIMARY_COLOR + "Online: " + ClubSpigot.VALUE_COLOR + Bukkit.getOnlinePlayers().size() +
				"/" + Bukkit.getMaxPlayers());
		sender.sendMessage(ClubSpigot.PRIMARY_COLOR + "Memory: " + ClubSpigot.VALUE_COLOR +
		                   ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 2) / 1048576L +
		                   "/" + Runtime.getRuntime().totalMemory() / 1048576L + " MB");
		sender.sendMessage(ClubSpigot.PRIMARY_COLOR + "Uptime: " + ClubSpigot.VALUE_COLOR +
		                   DateUtil.formatDateDiff(ManagementFactory.getRuntimeMXBean().getStartTime()));
		sender.sendMessage(ClubSpigot.PRIMARY_COLOR + "Entities: " + ClubSpigot.VALUE_COLOR + totalEntities);
		sender.sendMessage(ClubSpigot.PRIMARY_COLOR + "Last Tick Time: " + ClubSpigot.VALUE_COLOR +
		                   (System.currentTimeMillis() - MinecraftServer.LAST_TICK_TIME) + "ms");

		return true;
	}
}
