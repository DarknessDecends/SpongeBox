package me.darknessdecends.spongebox;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Torrey Mother Fucking Frith
 */
public class SpongeBox extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        log.info("[SpongeBox] SpongeBox is ready to Teleport since Flexo is a FuckTard!");
    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        log.info("[SpongeBox] SpongeBox is off!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("sponge")) {
            if (!(args.length == 1)) {
                return false;
            } else {
                log.info(args[0]);
                for (Player player : sender.getServer().getOnlinePlayers()) {
                    log.info(player.getPlayerListName());
                    if (player.getPlayerListName().equals(args[0])) {
                        String worldName = getConfig().getString("world");
                        double xCoord = getConfig().getDouble("sponge-box-x-coord");
                        double yCoord = getConfig().getDouble("sponge-box-y-coord");
                        double zCoord = getConfig().getDouble("sponge-box-z-coord");
                        Location box = new Location(getServer().getWorld(worldName), xCoord, yCoord, zCoord);
                        player.teleport(box);
                        player.setPlayerWeather(WeatherType.DOWNFALL);
                        player.sendMessage(ChatColor.YELLOW + "You're in a sponge box and it's rainy and dark, you should feel sad =(");
                        PotionEffect eppe = new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 1);
                        player.addPotionEffect(eppe);
                        player.setPlayerTime(12000, true);
                        sender.sendMessage(ChatColor.DARK_RED + "Sadness should commence shortly!");
                    }
                }
                sender.sendMessage(ChatColor.DARK_RED + "Player not found!");
                return true;
            }
        }
        if(cmd.getName().equalsIgnoreCase("spongeboxreload")){
            this.reloadConfig();
        }
        return false;
    }
}
