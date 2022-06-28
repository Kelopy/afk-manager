package me.kelopy.afkmanager.listeners;

import me.kelopy.afkmanager.manager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        manager.playerJoined(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        manager.playerLeft(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        manager.playerMoved(e.getPlayer());
    }

}
