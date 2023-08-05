package io.github.agus5534.tradesrebalance.villager.wandering;

import org.bukkit.inventory.ItemStack;

public abstract class WanderingTrade {
    protected ItemStack item;
    protected int price, maxUses;
    protected boolean special;

    public boolean special() {
        return special;
    }

    public ItemStack item() {
        return item;
    }

    public int price() {
        return price;
    }

    public int maxUses() {
        return maxUses;
    }

    protected WanderingTrade(ItemStack item, int price, int maxUses, boolean special) {
        this.item = item;
        this.price = price;
        this.maxUses = maxUses;
        this.special = special;
    }
}
