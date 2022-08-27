package io.github.leackybee.playertracker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PlayerTracker extends JavaPlugin {

    static HashMap<Player, Player> trackingList = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("track").setExecutor(new TrackPlayer());
        this.getCommand("untrack").setExecutor(new TrackPlayer());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for(Player player : trackingList.keySet()){
                player.setCompassTarget(trackingList.get(player).getLocation());
            }
        }, 1, 1);

        Bukkit.getPluginManager().registerEvents(new RespawnListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
