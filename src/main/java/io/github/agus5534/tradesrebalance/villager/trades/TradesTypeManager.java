package io.github.agus5534.tradesrebalance.villager.trades;

import io.github.agus5534.tradesrebalance.utils.Randomizer;
import io.github.agus5534.tradesrebalance.villager.VillagerTrade;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TradesTypeManager {

    private final List<VillagerTrade> normalTrades;
    private final VillagerTrade specialTrade;
    private final List<Enchantment> enchantments;

    private final HashMap<Integer, List<VillagerTrade>> levelTrades;
    public TradesTypeManager(List<VillagerTrade> tradeList) {
        this.normalTrades = tradeList.stream().filter(villagerTrade ->!villagerTrade.isSpecial()).toList();
        this.specialTrade = tradeList.stream().filter(VillagerTrade::isSpecial).findFirst().orElse(normalTrades.get(0));
        this.levelTrades = new HashMap<>();
        this.enchantments = new ArrayList<>();


        tradeList.forEach(villagerTrade -> {
            enchantments.addAll(villagerTrade.item().getEnchantments().keySet());

            if(levelTrades.get(villagerTrade.bookLevel()) == null) {
                levelTrades.put(villagerTrade.bookLevel(), List.of(villagerTrade));
                return;
            }

            var list = new ArrayList<>(levelTrades.get(villagerTrade.bookLevel()));
            list.add(villagerTrade);

            levelTrades.replace(villagerTrade.bookLevel(), list);
        });
    }

    public List<VillagerTrade> getNormalTrades() {
        return normalTrades;
    }

    public boolean containsEnchantment(Enchantment enchantment) {
        return enchantments.contains(enchantment);
    }

    public VillagerTrade getSpecialTrade() {
        return specialTrade;
    }

    public VillagerTrade getRandomTrade() {
        int random = ThreadLocalRandom.current().nextInt(1, 100);
        /* TODO alpha randomization
            40% - Level 1
            30% - Level 2
            18% - Level 3
            8% - Level 4
            4% - Level 5
         */
        if(random < 40) {
            return get(1);
        } else if(random < 70) {
            return get(2);
        } else if(random < 88) {
            return get(3);
        } else if(random < 96) {
            return get(4);
        } else {
            return get(5);
        }
    }

    private VillagerTrade get(int level) {
        if(!levelTrades.containsKey(level)) {
            if(level == 5) {
                return get(1);
            } else {
                return get(level+1);
            }
        }

        var list = levelTrades.get(level);
        list = list.stream().filter(villagerTrade -> !villagerTrade.isSpecial()).toList();
        return Randomizer.get(list);
    }



}
