package io.github.agus5534.tradesrebalance.villager.trades;

import io.github.agus5534.tradesrebalance.villager.VillagerTrade;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;

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
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_INFINITE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.THORNS, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.THORNS, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.THORNS, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FIRE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FIRE, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FIRE, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DIG_SPEED, 3, true)
                )
        );

        tradeHashMap.put(
                Villager.Type.JUNGLE,
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_DAMAGE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_DAMAGE, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_DAMAGE, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_DAMAGE, 4, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_DAMAGE, 5, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_PROJECTILE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_PROJECTILE, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_PROJECTILE, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_PROJECTILE, 4, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FALL, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FALL, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FALL, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_FALL, 4, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DURABILITY, 2, true)
                )
        );

        tradeHashMap.put(
                Villager.Type.PLAINS,
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ARTHROPODS, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ARTHROPODS, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ARTHROPODS, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ARTHROPODS, 4, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ARTHROPODS, 5, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_UNDEAD, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_UNDEAD, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_UNDEAD, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_UNDEAD, 4, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_UNDEAD, 5, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_KNOCKBACK, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_KNOCKBACK, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true)
                )
        );

        tradeHashMap.put(
                Villager.Type.SAVANNA,
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.SWEEPING_EDGE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.SWEEPING_EDGE, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.SWEEPING_EDGE, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.BINDING_CURSE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.KNOCKBACK, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.KNOCKBACK, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DAMAGE_ALL, 3, true)
                )
        );

        tradeHashMap.put(
                Villager.Type.SNOW,
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.FROST_WALKER, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.FROST_WALKER, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.LOOT_BONUS_MOBS, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.LOOT_BONUS_MOBS, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.LOOT_BONUS_MOBS, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.WATER_WORKER, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.SILK_TOUCH, 1, true)
                )
        );

        tradeHashMap.put(
                Villager.Type.SWAMP,
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.VANISHING_CURSE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.OXYGEN, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.OXYGEN, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.OXYGEN, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DEPTH_STRIDER, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DEPTH_STRIDER, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.DEPTH_STRIDER, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.MENDING, 1, true)
                )
        );

        tradeHashMap.put(
                Villager.Type.TAIGA,
                List.of(
                        VillagerTrade.ofEnchantedBook(Enchantment.ARROW_FIRE, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.FIRE_ASPECT, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.FIRE_ASPECT, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_EXPLOSIONS, 1, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_EXPLOSIONS, 2, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_EXPLOSIONS, 3, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.PROTECTION_EXPLOSIONS, 4, false),
                        VillagerTrade.ofEnchantedBook(Enchantment.LOOT_BONUS_BLOCKS, 2, true)
                )
        );
    }
}
