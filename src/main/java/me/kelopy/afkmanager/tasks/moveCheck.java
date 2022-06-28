package me.kelopy.afkmanager.tasks;

import me.kelopy.afkmanager.manager;

public class moveCheck implements Runnable {

    @Override
    public void run() {
        System.out.println("Checking players AFK status...");
        manager.checkAllPlayersAFKStatus();
    }

}
