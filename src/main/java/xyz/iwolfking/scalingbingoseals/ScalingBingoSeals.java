package xyz.iwolfking.scalingbingoseals;

import iskallia.vault.item.crystal.CrystalData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.iwolfking.scalingbingoseals.config.ScalingBingoSealsConfig;
import xyz.iwolfking.scalingbingoseals.init.VHAPIIntegration;
import xyz.iwolfking.scalingbingoseals.objective.ScalingBingoCrystalObjective;
import xyz.iwolfking.vhapi.api.LoaderRegistry;
import xyz.iwolfking.vhapi.api.events.VaultConfigEvent;
import xyz.iwolfking.vhapi.api.loaders.Processors;
import xyz.iwolfking.vhapi.api.loaders.lib.VHAPIDataLoader;
import xyz.iwolfking.vhapi.api.util.VHAPIProcesserUtils;

@Mod("scalingbingoseals")
public class ScalingBingoSeals {

    public ScalingBingoSeals() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ScalingBingoSealsConfig.COMMON_SPEC, "scaling-bingo-seals-common.toml");
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CrystalData.OBJECTIVE.register("scaling_bingo", ScalingBingoCrystalObjective.class, ScalingBingoCrystalObjective::new);
        ScalingBingoCrystalObjective.DEFAULT_HEIGHT = ScalingBingoSealsConfig.COMMON.defaultHeight.get();
        ScalingBingoCrystalObjective.DEFAULT_WIDTH = ScalingBingoSealsConfig.COMMON.defaultWidth.get();
    }

    @SubscribeEvent
    public void onServerStarting(VaultConfigEvent.Begin event) {
        VHAPIIntegration.initiateConfigs();
    }
}
