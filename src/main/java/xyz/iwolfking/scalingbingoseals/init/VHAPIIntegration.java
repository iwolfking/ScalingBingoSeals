package xyz.iwolfking.scalingbingoseals.init;

import net.minecraft.resources.ResourceLocation;
import xyz.iwolfking.scalingbingoseals.ScalingBingoSeals;
import xyz.iwolfking.scalingbingoseals.config.ScalingBingoSealsConfig;
import xyz.iwolfking.vhapi.api.util.VHAPIProcesserUtils;

import java.io.IOException;
import java.io.InputStream;

public class VHAPIIntegration {
    public static void initiateConfigs() {
        if(ScalingBingoSealsConfig.COMMON.useBingoSeal.get()) {
            registerManualConfigFile("/vhapi_configs/replace_bingo_seal.json", new ResourceLocation("scaling_bingo","vault/crystal/replace_bingo_seal"));
        }

        if(ScalingBingoSealsConfig.COMMON.useArchitectSeal.get()) {
            registerManualConfigFile("/vhapi_configs/use_architect_seal.json", new ResourceLocation("scaling_bingo","vault/crystal/replace_architect_seal"));
        }

    }

    public static void registerManualConfigFile(String filePath, ResourceLocation vhapiRegistryId) {
        try (InputStream stream = ScalingBingoSeals.class.getResourceAsStream(filePath)) {
            if (stream == null) {
                throw new IOException();
            }
            VHAPIProcesserUtils.addManualConfigFile(stream, vhapiRegistryId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
