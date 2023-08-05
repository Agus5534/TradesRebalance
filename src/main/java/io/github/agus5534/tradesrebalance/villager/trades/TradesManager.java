package io.github.agus5534.tradesrebalance.villager.trades;

import io.github.agus5534.tradesrebalance.villager.VillagerTrade;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class TradesManager {
    private final HashMap<Villager.Type, List<VillagerTrade>> tradeHashMap;
    private final HashMap<Villager.Type, TradesTypeManager> typeTradesTypeManagerHashMap;

    public TradesManager() {
        this.tradeHashMap = new HashMap<>();
        this.typeTradesTypeManagerHashMap = new HashMap<>();

        this.configEnchantments();

        tradeHashMap.forEach((type, villagerTrades) -> typeTradesTypeManagerHashMap.put(type, new TradesTypeManager(villagerTrades)));
    }

    public TradesTypeManager getType(Villager villager) {
        return typeTradesTypeManagerHashMap.get(villager.getVillagerType());
    }

    private void configEnchantments() {
        tradeHashMap.put(
                Villager.Type.DESERT,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.DIG_SPEED, 3, true),
                        Enchantment.ARROW_INFINITE, Enchantment.THORNS, Enchantment.PROTECTION_FIRE
                )
        );

        tradeHashMap.put(
                Villager.Type.JUNGLE,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.DURABILITY, 2, true),
                        Enchantment.ARROW_DAMAGE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_FALL
                )
        );

        tradeHashMap.put(
                Villager.Type.PLAINS,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true),
                        Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_UNDEAD, Enchantment.ARROW_KNOCKBACK
                )
        );

        tradeHashMap.put(
                Villager.Type.SAVANNA,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ALL, 3, true),
                        Enchantment.SWEEPING_EDGE, Enchantment.BINDING_CURSE, Enchantment.KNOCKBACK
                )
        );

        tradeHashMap.put(
                Villager.Type.SNOW,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.SILK_TOUCH, 1, true),
                        Enchantment.FROST_WALKER, Enchantment.LOOT_BONUS_MOBS, Enchantment.WATER_WORKER
                )
        );

        tradeHashMap.put(
                Villager.Type.SWAMP,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.MENDING, 1, true),
                        Enchantment.VANISHING_CURSE, Enchantment.OXYGEN, Enchantment.DEPTH_STRIDER
                )
        );

        tradeHashMap.put(
                Villager.Type.TAIGA,
                enchantmentTrades(
                        VillagerTrade.ofEnchantedBook(Enchantment.LOOT_BONUS_BLOCKS, 2, true),
                        Enchantment.ARROW_FIRE, Enchantment.FIRE_ASPECT, Enchantment.PROTECTION_EXPLOSIONS
                )
        );
    }

    private List<VillagerTrade> enchantmentTrades(VillagerTrade special, Enchantment... enchantments) {
        var list = new ArrayList<VillagerTrade>();

        list.add(special);

        for (Enchantment enchantment : enchantments) {
            for(int i = enchantment.getStartLevel(); i <= enchantment.getMaxLevel() ; i++) {
                list.add(VillagerTrade.ofEnchantedBook(enchantment, i, false));
            }
        }

        return list;
    }
}
