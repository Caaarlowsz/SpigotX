package club.minemen.spigot;

import club.minemen.spigot.command.KnockbackCommand;
import club.minemen.spigot.handler.MovementHandler;
import club.minemen.spigot.handler.PacketHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.MinecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;

public enum ClubSpigot {
    INSTANCE;

	public static final String PRIMARY_COLOR = ChatColor.WHITE.toString();
	public static final String VALUE_COLOR = ChatColor.WHITE.toString();

    private ClubSpigotConfig config;
    private Set<PacketHandler> packetHandlers = new HashSet<>();
    private Set<MovementHandler> movementHandlers = new HashSet<>();

    public ClubSpigotConfig getConfig() {
        return this.config;
    }

    public Set<PacketHandler> getPacketHandlers() {
        return this.packetHandlers;
    }

    public Set<MovementHandler> getMovementHandlers() {
        return this.movementHandlers;
    }

    public void setConfig(ClubSpigotConfig config) {
        this.config = config;
    }

	public void addPacketHandler(PacketHandler handler) {
		this.packetHandlers.add(handler);
	}

	public void addMovementHandler(MovementHandler handler) {
		this.movementHandlers.add(handler);
	}

	public void registerCommands() {
		Map<String, Command> commands = new HashMap<>();
		commands.put("knockback", new KnockbackCommand());
		for (Map.Entry<String, Command> entry : commands.entrySet()) {
			MinecraftServer.getServer().server.getCommandMap().register(entry.getKey(), "zSpigot", entry.getValue());
		}
	}
}
