//
// Created by BONNe
// Copyright - 2025
//


package xyz.iwolfking.scalingbingoseals.mixin;


import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.objective.CrystalObjective;
import xyz.iwolfking.scalingbingoseals.util.INamedObjective;


/**
 * This mixin adds default name for crystal objectives.
 */
@Mixin(value = CrystalObjective.class, remap = false)
public abstract class MixinCrystalObjective implements INamedObjective
{
    @Intrinsic
    @Override
    public String getCrystalName()
    {
        String type = CrystalData.OBJECTIVE.getType((CrystalObjective) (Object) this);
        return scalingbingoseals$getVaultObjective(type);
    }


    @Unique
    public String scalingbingoseals$getVaultObjective(String key)
    {
        return switch (key == null ? "" : key.toLowerCase())
        {
            case "boss" -> "Guardians Crystal";
            case "monolith" -> "Brazier Crystal";
            case "empty", "", "pool", "null", "compound" -> "Vault Crystal";
            default -> key.substring(0, 1).toUpperCase() + key.substring(1) + " Crystal";
        };
    }
}
