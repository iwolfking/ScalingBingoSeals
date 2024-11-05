package xyz.iwolfking.scalingbingoseals.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.SealAnvilRecipe;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.NameTagItem;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.scalingbingoseals.objective.ScalingBingoCrystalObjective;

@Mixin(value = SealAnvilRecipe.class, remap = false)
public class MixinApplySealRecipe {
    @Inject(method = "onSimpleCraft", at = @At(value = "INVOKE", target = "Liskallia/vault/item/crystal/CrystalData;write(Lnet/minecraft/world/item/ItemStack;)Liskallia/vault/item/crystal/CrystalData;", shift = At.Shift.AFTER))
    private void updateItemName(AnvilContext context, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 2) ItemStack output) {
        CrystalData outputData = CrystalData.read(output);
        if(outputData.getObjective() instanceof ScalingBingoCrystalObjective scalingBingoCrystalObjective) {
            context.setName(scalingBingoCrystalObjective.getCrystalName());
        }
    }
}
