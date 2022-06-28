package me.kelopy.afkmanager.commands;

import me.kelopy.afkmanager.manager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFK implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p){

            if(manager.toggleAFKStatus(p)){

                p.sendMessage(ChatColor.GRAY + "You're now " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");

                manager.announceToOthers(p, true);

            }else{
                p.sendMessage(ChatColor.GRAY + "You're no longer " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");

                manager.announceToOthers(p, false);
            }

        }else{
            System.out.println("This command can only be executed by a player.");
        }

        return true;
    }
}
