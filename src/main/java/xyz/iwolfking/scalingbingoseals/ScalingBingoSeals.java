package xyz.iwolfking.scalingbingoseals;

import com.mojang.logging.LogUtils;
import iskallia.vault.item.crystal.CrystalData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.iwolfking.scalingbingoseals.config.ScalingBingoSealsConfig;
import xyz.iwolfking.scalingbingoseals.init.VHAPIIntegration;
import xyz.iwolfking.scalingbingoseals.objective.ScalingBingoCrystalObjective;
import xyz.iwolfking.vhapi.VHAPI;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("scalingbingoseals")
public class ScalingBingoSeals {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ScalingBingoSeals() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ScalingBingoSealsConfig.COMMON_SPEC, "scaling-bingo-seals-common.toml");
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

        CrystalData.OBJECTIVE.register("scaling_bingo", ScalingBingoCrystalObjective.class, ScalingBingoCrystalObjective::new);
        VHAPIIntegration.initiateConfigs();
        ScalingBingoCrystalObjective.DEFAULT_HEIGHT = ScalingBingoSealsConfig.COMMON.defaultHeight.get();
        ScalingBingoCrystalObjective.DEFAULT_WIDTH = ScalingBingoSealsConfig.COMMON.defaultWidth.get();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
