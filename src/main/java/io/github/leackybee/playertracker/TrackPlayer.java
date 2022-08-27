package io.github.leackybee.playertracker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TrackPlayer implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if((!(commandSender instanceof Player) || strings.length != 1) && !command.getName().equals("untrack")){
            return false;
        }

        Player player = (Player) commandSender;

        if(command.getName().equals("untrack")){
            PlayerTracker.trackingList.remove(player);
            return true;
        }

        Player target = Bukkit.getPlayer(strings[0]);
        PlayerTracker.trackingList.put(player, target);

        if(player.getInventory().contains(Material.COMPASS)){
            ItemStack compass = new ItemStack(Material.COMPASS);
            compass.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            player.getInventory().addItem(compass);
            assert target != null;
            player.setCompassTarget(target.getLocation());
        }
        return true;

    }
}
