package xyz.iwolfking.scalingbingoseals.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.recipe.anvil.AnvilContext;
import iskallia.vault.recipe.anvil.SealAnvilRecipe;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import xyz.iwolfking.scalingbingoseals.config.ScalingBingoSealsConfig;
import xyz.iwolfking.scalingbingoseals.util.INamedObjective;


@Mixin(value = SealAnvilRecipe.class, remap = false)
public class MixinApplySealRecipe {
    @Inject(method = "onSimpleCraft", at = @At(value = "INVOKE", target = "Liskallia/vault/item/crystal/CrystalData;write(Lnet/minecraft/world/item/ItemStack;)Liskallia/vault/item/crystal/CrystalData;", shift = At.Shift.AFTER))
    private void updateItemName(AnvilContext context, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) ItemStack primary, @Local(ordinal = 2) ItemStack output) {
        // Do nothing if name change is disabled
        if (!ScalingBingoSealsConfig.COMMON.shouldChangeCrystalName.get())
        {
            return;
        }

        CrystalData inputData = CrystalData.read(primary);
        CrystalData outputData = CrystalData.read(output);

        if (((INamedObjective) inputData.getObjective()).getCrystalName().
            equals(((INamedObjective) outputData.getObjective()).getCrystalName()))
        {
            // Name change is not required.
            return;
        }

        // Set name based on objective
        context.setName(((INamedObjective) outputData.getObjective()).getCrystalName());
        // Name change in anvil cost 1 level.
        context.setLevelCost(-1);
    }
}
