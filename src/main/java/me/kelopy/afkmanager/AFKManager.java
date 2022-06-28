package me.kelopy.afkmanager;

import me.kelopy.afkmanager.commands.AFK;
import me.kelopy.afkmanager.commands.isAFK;
import me.kelopy.afkmanager.listeners.AFKListener;
import me.kelopy.afkmanager.tasks.moveCheck;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AFKManager extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("AFK Manager is tracking players...");

        getCommand("afk").setExecutor(new AFK());
        getCommand("isafk").setExecutor(new isAFK());
        getServer().getPluginManager().registerEvents(new AFKListener(), this);
        Bukkit.getScheduler().runTaskTimer(this, new moveCheck(), 0L, 600L);
    }

}
