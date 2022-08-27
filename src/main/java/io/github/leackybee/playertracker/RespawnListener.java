package io.github.leackybee.playertracker;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class RespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if(PlayerTracker.trackingList.containsKey(player)){
            ItemStack compass = new ItemStack(Material.COMPASS);
            compass.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            player.getInventory().addItem(compass);
        }
    }
}
