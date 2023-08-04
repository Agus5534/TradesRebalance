package io.github.agus5534.tradesrebalance.listeners;

import io.github.agus5534.tradesrebalance.villager.trades.TradesManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VillagerListener implements Listener {

    private final TradesManager tradesManager;
    public VillagerListener(TradesManager tradesManager) {
        this.tradesManager = tradesManager;
    }

    @EventHandler
    public void onAcquireTrade(VillagerAcquireTradeEvent event) {
        if(event.getEntityType().equals(EntityType.WANDERING_TRADER)) {
            return;
        }

        var villager = (Villager)event.getEntity();

        if(villager.getProfession() != Villager.Profession.LIBRARIAN) { return; }

        var recipe = fixedRecipe(villager, event.getRecipe());

        event.setRecipe(recipe);

        if(villager.getVillagerLevel() == 5) {
            generateSpecialRecipe(villager, recipe);
        }
    }


    public void generateSpecialRecipe(Villager villager, MerchantRecipe recipe) {
        var typeManager = tradesManager.getType(villager);
        var trade = typeManager.getSpecialTrade();

        var cost = ThreadLocalRandom.current().nextInt(trade.min(), trade.max());
        var rec = new MerchantRecipe(trade.item(), recipe.getUses(), recipe.getMaxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());
        rec.setIngredients(
                List.of
                        (
                                new ItemStack(Material.EMERALD, cost),
                                new ItemStack(Material.BOOK, 1)
                        )
        );

        var list = new ArrayList<>(villager.getRecipes());
        list.add(rec);

        villager.setRecipes(list);
    }
    public MerchantRecipe fixedRecipe(Villager villager, MerchantRecipe recipe) {
        var typeManager = tradesManager.getType(villager);

        if(recipe.getResult().getType() != Material.ENCHANTED_BOOK) {
            return recipe;
        }

        var result = recipe.getResult();
        var enchantment = (EnchantmentStorageMeta)result.getItemMeta();

        var ench = enchantment.getStoredEnchants().keySet().stream().findFirst().orElse(null);

        if(ench == null || typeManager.containsEnchantment(ench)) {
            return recipe;
        }

        var randBook = typeManager.getRandomTrade();
        var cost = ThreadLocalRandom.current().nextInt(randBook.min(), randBook.max());
        var rec = new MerchantRecipe(randBook.item(), recipe.getUses(), recipe.getMaxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());
        rec.setIngredients(
                List.of(
                        new ItemStack(Material.EMERALD, cost),
                        new ItemStack(Material.BOOK, 1)
                )
        );

        return rec;
    }
}
