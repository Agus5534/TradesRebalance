package io.github.agus5534.tradesrebalance.villager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class VillagerTrade {

    private final ItemStack item;
    private final int priceMin, priceMax;
    private Integer bookLevel;
    private final boolean special;

    private VillagerTrade(ItemStack item, int priceMin, int priceMax, boolean special, Integer bookLevel) {
        this.item = item;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.special = special;
        this.bookLevel = bookLevel;
    }

    public int max() {
        return priceMax;
    }

    public int min() {
        return priceMin;
    }

    public ItemStack item() {
        return item;
    }

    public boolean isSpecial() {
        return special;
    }

    public Integer bookLevel() {
        return bookLevel;
    }

    public static VillagerTrade of(ItemStack item, int priceMin, int priceMax, boolean special) {
        return new VillagerTrade(item, priceMin, priceMax, special, null);
    }

    public static VillagerTrade ofEnchantedBook(Enchantment enchantment, int level, int priceMin, int priceMax, boolean special) {
        var item = new ItemStack(Material.ENCHANTED_BOOK,1);
        var meta = (EnchantmentStorageMeta)item.getItemMeta();

        meta.addStoredEnchant(enchantment, level, false);
        item.setItemMeta(meta);

        return new VillagerTrade(item, priceMin, priceMax, special, level);
    }

    public static VillagerTrade ofEnchantedBook(Enchantment enchantment, int level, boolean special) {
        var item = new ItemStack(Material.ENCHANTED_BOOK,1);
        var meta = (EnchantmentStorageMeta)item.getItemMeta();

        meta.addStoredEnchant(enchantment, level, false);
        item.setItemMeta(meta);
        int priceMin;
        int priceMax;

        switch (level) {
            case 1 -> {
                priceMin = 5;
                priceMax = 19;
            }
            case 2 -> {
                priceMin = 8;
                priceMax = 32;
            }
            case 3 -> {
                priceMin = 11;
                priceMax = 45;
            }
            case 4 -> {
                priceMin = 14;
                priceMax = 58;
            }
            default -> {
                priceMin = 17;
                priceMax = 64;
            }
        }

        return new VillagerTrade(item, priceMin, priceMax, special, level);
    }
}
