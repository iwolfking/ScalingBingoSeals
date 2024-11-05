package xyz.iwolfking.scalingbingoseals.mixin;

import iskallia.vault.core.vault.player.ClassicListenersLogic;
import net.minecraftforge.registries.IForgeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.vhapi.api.registry.VaultObjectiveRegistry;
import xyz.iwolfking.vhapi.api.registry.objective.CustomObjectiveRegistryEntry;

import java.util.Iterator;

@Mixin(value = ClassicListenersLogic.class, remap = false)
public class MixinClassicListenersLogic {

    @Inject(
            method = {"getVaultObjective"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void addCustomObjectiveNames(String key, CallbackInfoReturnable<String> cir) {
        if(key.equals("scaling_bingo")) {
           cir.setReturnValue("Bingo");
        }
    }
}
