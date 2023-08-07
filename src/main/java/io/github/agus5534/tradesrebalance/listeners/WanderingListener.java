package io.github.agus5534.tradesrebalance.listeners;

import io.github.agus5534.tradesrebalance.utils.Randomizer;
import io.github.agus5534.tradesrebalance.utils.Version;
import io.github.agus5534.tradesrebalance.villager.wandering.WanderingBuyTrade;
import io.github.agus5534.tradesrebalance.villager.wandering.WanderingSellTrade;
import io.github.agus5534.tradesrebalance.villager.wandering.WanderingTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WanderingListener implements Listener {

    public List<WanderingTrade> tradeList = new ArrayList<>();
    List<WanderingTrade> specialTradeList = new ArrayList<>();

    public WanderingListener() {
        this.configureTrades();
    }

    @EventHandler
    public void onAcquireTrade(VillagerAcquireTradeEvent event) {
        if(event.getEntityType().equals(EntityType.WANDERING_TRADER)) {
            var wandering = (WanderingTrader)event.getEntity();

            if(wandering.getRecipes().size() == 5) {
                wandering.setRecipes(wanderingFixed(event.getRecipe()));
                event.setCancelled(true);
            }
        }
    }

    private List<MerchantRecipe> wanderingFixed(MerchantRecipe recipe) {
        List<MerchantRecipe> merchantRecipes = new ArrayList<>();
        List<WanderingTrade> trades = new ArrayList<>();

        Randomizer.get(5, tradeList).forEach(trades::add);
        trades.add(Randomizer.get(specialTradeList));

        trades.forEach(wanderingTrade -> {
            if(wanderingTrade instanceof WanderingBuyTrade) {
                var rec = new MerchantRecipe(wanderingTrade.item(), recipe.getUses(), wanderingTrade.maxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());
                var list = new ArrayList<ItemStack>();

                if(((WanderingBuyTrade) wanderingTrade).costOne() != null) {
                    list.add(((WanderingBuyTrade) wanderingTrade).costOne());
                }
                if(((WanderingBuyTrade) wanderingTrade).costTwo() != null) {
                    list.add(((WanderingBuyTrade) wanderingTrade).costTwo());
                }

                rec.setIngredients(list);
                merchantRecipes.add(rec);
            }

            if(wanderingTrade instanceof WanderingSellTrade) {
                var rec = new MerchantRecipe(wanderingTrade.item(), recipe.getUses(), wanderingTrade.maxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());

                rec.setIngredients(
                        List.of(
                                new ItemStack(Material.EMERALD, wanderingTrade.price())
                        )
                );
                merchantRecipes.add(rec);
            }
        });

        return merchantRecipes;
    }

    private void configureTrades() {
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        1, 1, 12, false,
                        Material.ALLIUM, Material.AZURE_BLUET, Material.CORNFLOWER, Material.DANDELION, Material.ORANGE_TULIP, Material.OXEYE_DAISY,
                        Material.PINK_TULIP, Material.POPPY, Material.RED_TULIP, Material.WHITE_TULIP, Material.FERN, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM,
                        Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.WHEAT_SEEDS, Material.VINE

                )
        );

        tradeList.addAll(
             WanderingSellTrade.ofList(
                     1, 1, 8, false,
                     Material.BLUE_ORCHID, Material.SUGAR_CANE
             )
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.LILY_OF_THE_VALLEY), 1, 7, false)
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.PUMPKIN), 1, 4, false)
        );
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        1, 2, 5, false,
                        Material.LILY_PAD, Material.MOSS_BLOCK, Material.POINTED_DRIPSTONE, Material.ROOTED_DIRT, Material.SMALL_DRIPLEAF
                )
        );
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        1, 3, 12, false,
                        Arrays.stream(Material.values()).filter(material -> material.name().endsWith("DYE")).toList()
                )
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.RED_SAND, 4), 1, 6, false)
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.SAND, 8), 1, 8, false)
        );
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        2, 1, 5, false,
                        Material.GLOWSTONE, Material.SEA_PICKLE
                )
        );
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        3, 1, 8, false,
                        Material.CACTUS, Material.BRAIN_CORAL_BLOCK, Material.BUBBLE_CORAL_BLOCK, Material.FIRE_CORAL_BLOCK, Material.HORN_CORAL_BLOCK, Material.TUBE_CORAL_BLOCK
                )
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.KELP), 3, 12, false)
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.SLIME_BALL), 4, 5, false)
        );
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        5, 1, 8, false,
                        Material.BIRCH_SAPLING, Material.ACACIA_SAPLING, Material.DARK_OAK_SAPLING, Material.JUNGLE_SAPLING
                )
        );
        tradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.NAUTILUS_SHELL), 5, 5, false)
        );

        specialTradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.GUNPOWDER), 1, 8, true)
        );
        specialTradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.PODZOL, 3), 3, 6, true)
        );
        specialTradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.PACKED_ICE), 3, 6, true)
        );
        specialTradeList.addAll(
                WanderingSellTrade.ofList(
                        5, 1, 4, true,
                        Material.TROPICAL_FISH_BUCKET, Material.PUFFERFISH_BUCKET
                )
        );
        specialTradeList.add(
                WanderingSellTrade.of(new ItemStack(Material.BLUE_ICE), 6, 6, true)
        );

        try {
            if(Version.CURRENT.getMinor() >= 19) {
                tradeList.add(
                        WanderingSellTrade.of(new ItemStack(getMaterialReflection("MANGROVE_PROPAGULE")), 5, 8, false)
                );

                //23w31a

                tradeList.add(
                        WanderingSellTrade.of(new ItemStack(getMaterialReflection("MANGROVE_LOG"), 8), 1, 4, false)
                );
            }

            if(Version.CURRENT.getMinor() >= 20) {
                tradeList.add(
                        WanderingSellTrade.of(new ItemStack(getMaterialReflection("CHERRY_SAPLING")), 5, 8, false)
                );

                //23w31a

                tradeList.add(
                        WanderingSellTrade.of(new ItemStack(getMaterialReflection("CHERRY_LOG"), 8), 1, 4, false)
                );
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 23w31a Changes

        var waterBottle = new ItemStack(Material.POTION);
        var meta = (PotionMeta)waterBottle.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        waterBottle.setItemMeta(meta);

        tradeList.add(
                WanderingBuyTrade.of(
                        new ItemStack(Material.EMERALD),
                        1, waterBottle, null, false
                )
        );
        tradeList.add(
                WanderingBuyTrade.of(
                        new ItemStack(Material.EMERALD, 2),
                        1, new ItemStack(Material.WATER_BUCKET), null, false

                )
        );
        tradeList.add(
                WanderingBuyTrade.of(
                        new ItemStack(Material.EMERALD, 2),
                        1, new ItemStack(Material.MILK_BUCKET), null, false

                )
        );
        tradeList.add(
                WanderingBuyTrade.of(
                        new ItemStack(Material.EMERALD, 3),
                        1, new ItemStack(Material.FERMENTED_SPIDER_EYE), null, false
                )
        );
        tradeList.add(
                WanderingBuyTrade.of(
                        new ItemStack(Material.EMERALD, 1),
                        1, new ItemStack(Material.BAKED_POTATO, 4), null, false
                )
        );
        tradeList.add(
                WanderingBuyTrade.of(
                        new ItemStack(Material.EMERALD),
                        1, new ItemStack(Material.HAY_BLOCK), null, false
                )
        );
        tradeList.addAll(
                WanderingSellTrade.ofList(
                        1, 8,4, false,
                        Arrays.stream(Material.values()).filter(m-> m.name().endsWith("LOG") && !m.name().contains("STRIPPED") && !m.isLegacy()).toList()
                )
        );

        var inviBottle = new ItemStack(Material.POTION);
        var inviMeta = (PotionMeta)inviBottle.getItemMeta();
        inviMeta.setBasePotionData(new PotionData(PotionType.INVISIBILITY));
        inviBottle.setItemMeta(inviMeta);

        tradeList.add(
                WanderingSellTrade.of(
                        inviBottle, 5, 1, false
                )
        );

        tradeList.add(
                WanderingSellTrade.of(
                        randomEnchant(new ItemStack(Material.IRON_PICKAXE)),
                        ThreadLocalRandom.current().nextInt(6, 20), 1, false
                )
        );
    }

    private Material getMaterialReflection(String name) throws ClassNotFoundException {
        Object o = null;
        var constants = Class.forName("org.bukkit.Material").getEnumConstants();
        for(var c : constants) {
            if(c.toString().equalsIgnoreCase(name)) {
                o = c;
            }
        }

        Material m = (Material)o;

        Bukkit.getLogger().info("Reflection: Obtained Material " + m.name());
        return m;
    }

    public ItemStack randomEnchant(ItemStack itemStack) {
        double n = ThreadLocalRandom.current().nextInt(1, 50);
        double maxEnch = ThreadLocalRandom.current().nextInt(1, 3);
        int i = 0;
        var meta = itemStack.getItemMeta();

        while (i < maxEnch) {
            List<Enchantment> availableEnchants = new ArrayList<>();

            Arrays.stream(Enchantment.values())
                    .filter(e -> e.canEnchantItem(itemStack) && !meta.hasEnchant(e))
                    .forEach(e -> availableEnchants.add(e));


            if(!availableEnchants.isEmpty()) {
                var enchantment = availableEnchants.get(new Random().nextInt(availableEnchants.size()));

                int level = new Random().nextInt(enchantment.getMaxLevel());

                if(level == 0) { level++; }

                if(enchantment.canEnchantItem(itemStack)) {
                    meta.addEnchant(enchantment,level, false);
                }
            }
            i++;
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
