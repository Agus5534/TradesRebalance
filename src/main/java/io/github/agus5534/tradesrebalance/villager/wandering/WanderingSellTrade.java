package io.github.agus5534.tradesrebalance.villager.wandering;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WanderingSellTrade extends WanderingTrade {
    private WanderingSellTrade(ItemStack item, int price, int maxUses, boolean special) {
        super(item, price, maxUses, special);
    }

    public static WanderingSellTrade of(ItemStack item, int price, int maxUses, boolean special) {
        return new WanderingSellTrade(item, price, maxUses, special);
    }

    public static List<WanderingSellTrade> ofList(int price, int count, int uses, boolean special, Material... materials) {
        List<WanderingSellTrade> l = new ArrayList<>();

        Arrays.stream(materials).forEach(m -> l.add(WanderingSellTrade.of(new ItemStack(m, count), price, uses, special)));

        return l;
    }

    public static List<WanderingSellTrade> ofList(int price, int count, int uses, boolean special, List<Material> materials) {
        List<WanderingSellTrade> l = new ArrayList<>();

        materials.forEach(m -> l.add(WanderingSellTrade.of(new ItemStack(m, count), price, uses, special)));

        return l;
    }
}
