package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModTags;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
       /* woodFromLogs(consumer, ModBlocks.COCONUT_WOOD.get(), ModBlocks.COCONUT_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_COCONUT_WOOD.get(), ModBlocks.STRIPPED_COCONUT_LOG.get());
        planksFromLogsTag(consumer, ModBlocks.COCONUT_PLANKS.get(), ModTags.Items.COCONUT_LOGS);
        slab(consumer, ModBlocks.COCONUT_SLAB.get(), ModBlocks.COCONUT_PLANKS.get());
        stair(consumer, ModBlocks.COCONUT_STAIRS.get(), ModBlocks.COCONUT_PLANKS.get());
        fence(consumer, ModBlocks.COCONUT_FENCE.get(), ModBlocks.COCONUT_PLANKS.get());
        fenceGate(consumer, ModBlocks.COCONUT_FENCE_GATE.get(), ModBlocks.COCONUT_PLANKS.get());
        door(consumer, ModBlocks.COCONUT_DOOR.get(), ModBlocks.COCONUT_PLANKS.get());
        trapdoor(consumer, ModBlocks.COCONUT_TRAPDOOR.get(), ModBlocks.COCONUT_PLANKS.get());
        button(consumer, ModBlocks.COCONUT_BUTTON.get(), ModBlocks.COCONUT_PLANKS.get());
        pressurePlate(consumer, ModBlocks.COCONUT_PRESSURE_PLATE.get(), ModBlocks.COCONUT_PLANKS.get());
        cookRecipes(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookRecipes(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRAB_CLAW.get()), ModItems.CRAB_MEAT.get(), 0.35F, 200).unlockedBy("has_crab_claw", has(ModItems.CRAB_CLAW.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.TROPICAL_STEW.get()).requires(ModItems.COCONUT_SLICE.get()).requires(ModItems.CRAB_MEAT.get()).unlockedBy("has_cooked_claw", has(ModItems.CRAB_MEAT.get())).save(consumer);
*/
        ShapedRecipeBuilder.shaped(ModBlocks.SANDCASTLE.get()).define('A', Blocks.SAND).define('B', ModBlocks.SEASHELL.get()).define('C', Items.STICK).pattern(" C ").pattern("ABA").pattern("AAA").unlockedBy(getHasName(ModBlocks.SEASHELL.get()), has(ModBlocks.SEASHELL.get())).save(consumer);
    }

    private static void cookRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String pCookingMethod, SimpleCookingSerializer<?> pCookingSerializer, int pCookingTime) {
        simpleCookingRecipe(pFinishedRecipeConsumer, pCookingMethod, pCookingSerializer, pCookingTime, ModItems.CRAB_CLAW.get(), ModItems.CRAB_MEAT.get(), 0.35F);
    }

    private static void simpleCookingRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String pCookingMethod, SimpleCookingSerializer<?> pCookingSerializer, int pCookingTime, ItemLike pIngredient, ItemLike pResult, float pExperience) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(pIngredient), pResult, pExperience, pCookingTime, pCookingSerializer).unlockedBy(getHasName(pIngredient), has(pIngredient)).save(pFinishedRecipeConsumer, getHasName(pResult) + "_from_" + pCookingMethod);
    }

    private static void planksFromLogsTag(Consumer<FinishedRecipe> consumer, ItemLike planks, Tag<Item> tag) {
        ShapelessRecipeBuilder.shapeless(planks, 4).requires(tag).group("planks").unlockedBy("has_log", has(ModTags.Items.COCONUT_LOGS)).save(consumer);
    }

    private static void woodFromLogs(Consumer<FinishedRecipe> consumer, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(wood, 3).define('#', log).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(log)).save(consumer);
    }

    private static void button(Consumer<FinishedRecipe> consumer, ItemLike button, ItemLike planks) {
        buttonBuilder(button, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static RecipeBuilder buttonBuilder(ItemLike button, Ingredient planks) {
        return ShapelessRecipeBuilder.shapeless(button).requires(planks);
    }

    private static void door(Consumer<FinishedRecipe> consumer, ItemLike door, ItemLike planks) {
        doorBuilder(door, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static RecipeBuilder doorBuilder(ItemLike door, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(door, 3).define('#', planks).pattern("##").pattern("##").pattern("##");
    }

    private static void fence(Consumer<FinishedRecipe> consumer, ItemLike fence, ItemLike planks) {
        fenceBuilder(fence, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }
    
    private static RecipeBuilder fenceBuilder(ItemLike fence, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(fence, 3).define('W', planks).define('#', Items.STICK).pattern("W#W").pattern("W#W");
    }

    private static void fenceGate(Consumer<FinishedRecipe> consumer, ItemLike fenceGate, ItemLike planks) {
        fenceGateBuilder(fenceGate, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static RecipeBuilder fenceGateBuilder(ItemLike fenceGate, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(fenceGate).define('#', Items.STICK).define('W', planks).pattern("#W#").pattern("#W#");
    }

    private static void pressurePlate(Consumer<FinishedRecipe> consumer, ItemLike pressurePlate, ItemLike planks) {
        pressurePlateBuilder(pressurePlate, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static RecipeBuilder pressurePlateBuilder(ItemLike pressurePlate, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(pressurePlate).define('#', planks).pattern("##");
    }

    private static void slab(Consumer<FinishedRecipe> consumer, ItemLike slab, ItemLike planks) {
        slabBuilder(slab, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static RecipeBuilder slabBuilder(ItemLike slab, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(slab, 6).define('#', planks).pattern("###");
    }

    private static void stair(Consumer<FinishedRecipe> consumer, ItemLike stair, ItemLike planks) {
        stairBuilder(stair, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }
    
    private static RecipeBuilder stairBuilder(ItemLike stairs, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(stairs, 4).define('#', planks).pattern("#  ").pattern("## ").pattern("###");
    }

    private static void trapdoor(Consumer<FinishedRecipe> consumer, ItemLike trapdoor, ItemLike planks) {
        trapdoorBuilder(trapdoor, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static RecipeBuilder trapdoorBuilder(ItemLike trapdoor, Ingredient planks) {
        return ShapedRecipeBuilder.shaped(trapdoor, 2).define('#', planks).pattern("###").pattern("###");
    }

    private static String getHasName(ItemLike item) {
        return "has_" + item.asItem().getRegistryName().getPath();
    }
}
