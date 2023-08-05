package io.github.agus5534.tradesrebalance.villager.wandering;

import org.bukkit.inventory.ItemStack;

public class WanderingBuyTrade extends WanderingTrade {

    private boolean special;
    private final ItemStack costOne, costTwo;

    private WanderingBuyTrade(ItemStack item, int maxUses, ItemStack costOne, ItemStack costTwo, boolean special) {
        super(item, 0, maxUses, special);
        this.costOne = costOne;
        this.costTwo = costTwo;
    }
    public ItemStack costOne() {
        return costOne;
    }

    public ItemStack costTwo() {
        return costTwo;
    }

    public static WanderingBuyTrade of(ItemStack item, int maxUses, ItemStack costOne, ItemStack costTwo, boolean special) {
        return new WanderingBuyTrade(item, maxUses, costOne, costTwo, special);
    }

}
