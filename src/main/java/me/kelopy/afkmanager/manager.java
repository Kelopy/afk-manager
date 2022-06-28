package me.kelopy.afkmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class manager {

    private static final long MOVEMENT_THRESHOLD = 60000L;

    private static final HashMap<Player, Long> lastMovement = new HashMap<>();
    private static final HashMap<Player, Boolean> previousData = new HashMap<>();

    public static void playerJoined(Player player){
        lastMovement.put(player, System.currentTimeMillis());
    }

    public static void playerLeft(Player player){
        lastMovement.remove(player);
    }

    public static boolean toggleAFKStatus(Player player){

        if (IsAFK(player)){
            previousData.put(player, false);
            lastMovement.put(player, System.currentTimeMillis());
            return false;
        }else{
            previousData.put(player, true);
            lastMovement.put(player, -1L);
            return true;
        }

    }

    public static void playerMoved(Player player){

        lastMovement.put(player, System.currentTimeMillis());

        checkPlayerAFKStatus(player);

    }

    public static boolean IsAFK(Player player){

        if(lastMovement.containsKey(player)){
            if(lastMovement.get(player) == -1L){
                return true;
            }else{
                long timeElapsed = System.currentTimeMillis() - lastMovement.get(player);
                return timeElapsed >= MOVEMENT_THRESHOLD;
            }
        }else{
            lastMovement.put(player, System.currentTimeMillis());
        }

        return false;
    }

    public static void checkAllPlayersAFKStatus(){

        for (Map.Entry<Player, Long> entry : lastMovement.entrySet()){
            checkPlayerAFKStatus(entry.getKey());
        }

    }

    public static void checkPlayerAFKStatus(Player player){
        if (lastMovement.containsKey(player)){

            boolean nowAFK = IsAFK(player);

            if (previousData.containsKey(player)){

                boolean wasAFK = previousData.get(player);

                if(wasAFK && !nowAFK){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You're no longer &bAFK&7."));
                    previousData.put(player, false);

                    announceToOthers(player, false);

                }else if(!wasAFK && nowAFK){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You're now &bAFK&7."));
                    previousData.put(player, true);

                    announceToOthers(player, true);
                }

            }else{
                previousData.put(player, nowAFK);
            }

        }
    }

    public static void announceToOthers(Player target, boolean isAFK){

        Bukkit.getServer().getOnlinePlayers()
                .forEach(player -> {
                    if (isAFK){
                        player.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY + " is now " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");
                    }else{
                        player.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY + " is no longer " + ChatColor.AQUA + "AFK" + ChatColor.GRAY + ".");
                    }
                });

    }

}
