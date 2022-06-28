package me.kelopy.afkmanager.commands;

import me.kelopy.afkmanager.manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class isAFK implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p){

            if(args.length == 0){

                if(manager.IsAFK(p)){
                    p.sendMessage(ChatColor.GRAY + "You're currently " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");
                }else{
                    p.sendMessage(ChatColor.GRAY + "You're not currently " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");
                }

            }else{
                Player target = Bukkit.getPlayerExact(args[0]);

                if(target != null){

                    if(manager.IsAFK(target)){
                        p.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY + " is currently " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");
                    }else{
                        p.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY + " isn't currently " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");
                    }

                }else{
                    p.sendMessage(ChatColor.RED + "Player not found");
                }

            }

        }else{
            System.out.println("This command can only be executed by a player.");
        }

        return true;
    }
}
