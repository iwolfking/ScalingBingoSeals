package xyz.iwolfking.scalingbingoseals.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

public class ScalingBingoSealsConfig
{

    public static class Common
    {
        //Features
        //Items
        //Gear
        public final ForgeConfigSpec.ConfigValue<Boolean> useBingoSeal;
        public final ForgeConfigSpec.ConfigValue<Boolean> useArchitectSeal;

        public final ForgeConfigSpec.ConfigValue<Integer> defaultHeight;
        public final ForgeConfigSpec.ConfigValue<Integer> defaultWidth;
        public final ForgeConfigSpec.ConfigValue<Integer> sealAddCount;
        public final ForgeConfigSpec.ConfigValue<Integer> maxSeals;

        public final ForgeConfigSpec.ConfigValue<Boolean> shouldChangeCrystalName;
        public final ForgeConfigSpec.ConfigValue<Integer> sizeToSetInfiniteLayout;

        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("Seal Replacement");
            useBingoSeal = builder.comment("Whether Bingo Seal should be replacing with Scaling Bingo objective.").define("useBingoSeal", true);
            useArchitectSeal = builder.comment("Whether Architect Seal should be replaced with Scaling Bingo Objective.").define("useArchitectSeal", false);
            builder.pop();
            builder.push("Scaling Bingo Config");
            defaultHeight = builder.comment("Default height of Bingo vault with a single Seal applied (default: 3)").define("defaultHeight", 3);
            defaultWidth = builder.comment("Default width of Bingo vault with a single Seal applied (default: 3)").define("defaultWidth", 3);
            sealAddCount = builder.comment("How much width and height each seal adds (default: 1)").define("sealAddCount", 1);
            maxSeals = builder.comment("Maximum number of seals that can be applied (default: 7)").define("maxSeals", 7);
            sizeToSetInfiniteLayout = builder.comment("If this is not 0, the size that the vault layout will start to be set to infinite. (default: 0)").define("sizeToSetInfiniteLayout", 0);
            builder.pop();
            builder.push("Server Settings");
            shouldChangeCrystalName = builder.comment("Whether when adding a seal in anvil should change crystal name based on Bingo board size (useful for server-side only usage, default: false)").define("shouldChangeCrystalName", false);
            builder.pop();
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    static //constructor
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

}