package io.github.agus5534.tradesrebalance.villager.trades;

import io.github.agus5534.tradesrebalance.utils.Randomizer;
import io.github.agus5534.tradesrebalance.villager.EnchantedBookTrade;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TradesTypeManager {

    private final List<EnchantedBookTrade> normalTrades;
    private final EnchantedBookTrade specialTrade;
    private final List<Enchantment> enchantments;

    private final HashMap<Integer, List<EnchantedBookTrade>> levelTrades;
    public TradesTypeManager(List<EnchantedBookTrade> tradeList) {
        this.normalTrades = tradeList.stream().filter(enchantedBookTrade ->!enchantedBookTrade.isSpecial()).toList();
        this.specialTrade = tradeList.stream().filter(EnchantedBookTrade::isSpecial).findFirst().orElse(normalTrades.get(0));
        this.levelTrades = new HashMap<>();
        this.enchantments = new ArrayList<>();


        tradeList.forEach(enchantedBookTrade -> {
            enchantments.addAll(enchantedBookTrade.item().getEnchantments().keySet());

            if(levelTrades.get(enchantedBookTrade.bookLevel()) == null) {
                levelTrades.put(enchantedBookTrade.bookLevel(), List.of(enchantedBookTrade));
                return;
            }

            var list = new ArrayList<>(levelTrades.get(enchantedBookTrade.bookLevel()));
            list.add(enchantedBookTrade);

            levelTrades.replace(enchantedBookTrade.bookLevel(), list);
        });
    }

    public List<EnchantedBookTrade> getNormalTrades() {
        return normalTrades;
    }

    public boolean containsEnchantment(Enchantment enchantment) {
        return enchantments.contains(enchantment);
    }

    public EnchantedBookTrade getSpecialTrade() {
        return specialTrade;
    }

    public EnchantedBookTrade getRandomTrade() {
        int random = ThreadLocalRandom.current().nextInt(1, 100);
        /*
            TODO create efficient randomization
            Actual:
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

    private EnchantedBookTrade get(int level) {
        if(!levelTrades.containsKey(level)) {
            return level == 5 ? get(1) : get(level+1);
        }

        var list = levelTrades.get(level);
        list = list.stream().filter(enchantedBookTrade -> !enchantedBookTrade.isSpecial()).toList();
        return Randomizer.get(list);
    }



}
