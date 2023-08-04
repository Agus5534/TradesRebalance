package io.github.agus5534.tradesrebalance;

import io.github.agus5534.tradesrebalance.listeners.VillagerListener;
import io.github.agus5534.tradesrebalance.villager.trades.TradesManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TradesRebalance extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new VillagerListener(new TradesManager()), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
