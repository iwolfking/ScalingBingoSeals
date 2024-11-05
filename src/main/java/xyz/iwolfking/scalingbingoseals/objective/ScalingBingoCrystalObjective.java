package xyz.iwolfking.scalingbingoseals.objective;

import com.google.gson.JsonObject;
import iskallia.vault.VaultMod;
import iskallia.vault.block.VaultCrateBlock;
import iskallia.vault.core.data.adapter.Adapters;
import iskallia.vault.core.random.RandomSource;
import iskallia.vault.core.vault.*;
import iskallia.vault.core.vault.objective.*;
import iskallia.vault.core.world.generator.GridGenerator;
import iskallia.vault.core.world.generator.layout.ClassicInfiniteLayout;
import iskallia.vault.core.world.generator.layout.VaultLayout;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.objective.BingoCrystalObjective;
import iskallia.vault.item.crystal.objective.CrystalObjective;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import xyz.iwolfking.scalingbingoseals.config.ScalingBingoSealsConfig;
import xyz.iwolfking.scalingbingoseals.mixin.accessors.BingoTaskConfigAccessor;

import java.util.List;
import java.util.Optional;

public class ScalingBingoCrystalObjective extends CrystalObjective {
    private float objectiveProbability;
    private int sealCount;

    public static int DEFAULT_WIDTH = 3;
    public static int DEFAULT_HEIGHT = 3;

    public ScalingBingoCrystalObjective(float objectiveProbability, int sealCount) {
        this.objectiveProbability = objectiveProbability;
        this.sealCount = sealCount;
    }

    public ScalingBingoCrystalObjective() {
        this.sealCount = 1;
    }

    @Override
    public Optional<Integer> getColor(float v) {
        return Optional.of(4821183);
    }

    @Override
    public void configure(Vault vault, RandomSource random) {
        int level = ((VaultLevel)vault.get(Vault.LEVEL)).get();
        vault.ifPresent(Vault.OBJECTIVES, (objectives) -> {
            ModConfigs.BINGO.generate(VaultMod.id("default"), level).ifPresent((task) -> {
                ((BingoTaskConfigAccessor)task.getConfig()).setWidth(getWidth());
                ((BingoTaskConfigAccessor)task.getConfig()).setHeight(getHeight());
                objectives.add(BingoObjective.of(task).add(GridGatewayObjective.of(this.objectiveProbability).add(AwardCrateObjective.ofConfig(VaultCrateBlock.Type.BINGO, "bingo", level, true)).add(VictoryObjective.of(300))));
            });
            objectives.add(BailObjective.create(true, new ResourceLocation[]{ClassicPortalLogic.EXIT}));
            objectives.add(DeathObjective.create(true));
            objectives.set(Objectives.KEY, CrystalData.OBJECTIVE.getType(this));
        });
    }

    @Override
    public void addText(List<Component> tooltip, int minIndex, TooltipFlag flag, float time) {
        tooltip.add((new TextComponent("Objective: ")).append((new TextComponent(getHeight() + "x" + getWidth() + " Bingo")).withStyle(Style.EMPTY.withColor((Integer)this.getColor(time).orElseThrow()))));
    }

    public int getHeight() {
        return DEFAULT_HEIGHT + sealCount - 1;
    }

    public int getWidth() {
        return DEFAULT_WIDTH + sealCount - 1;
    }

    public int getSealCount() {
        return Math.min(this.sealCount, ScalingBingoSealsConfig.COMMON.maxSeals.get());
    }

    public float getObjectiveProbability() {
        return this.objectiveProbability;
    }

    public Optional<CompoundTag> writeNbt() {
        CompoundTag nbt = new CompoundTag();
        Adapters.FLOAT.writeNbt(this.objectiveProbability).ifPresent((tag) -> {
            nbt.put("objective_probability", tag);
        });
        Adapters.INT.writeNbt(this.sealCount).ifPresent(tag -> {
            nbt.put("sealCount", tag);
        });
        return Optional.of(nbt);
    }

    public void readNbt(CompoundTag nbt) {
        this.objectiveProbability = (Float)Adapters.FLOAT.readNbt(nbt.get("objective_probability")).orElse(0.0F);
        this.sealCount = Adapters.INT.readNbt(nbt.get("sealCount")).orElse(1);
    }

    public Optional<JsonObject> writeJson() {
        JsonObject json = new JsonObject();
        Adapters.FLOAT.writeJson(this.objectiveProbability).ifPresent((tag) -> {
            json.add("objective_probability", tag);
        });
        Adapters.INT.writeJson(this.sealCount).ifPresent((tag) -> {
            json.add("sealCount", tag);
        });
        return Optional.of(json);
    }

    public String getCrystalName() {
        return getHeight() + "x" + getWidth() + " Bingo Crystal";
    }

    public void readJson(JsonObject json) {
        this.objectiveProbability = (Float)Adapters.FLOAT.readJson(json.get("objective_probability")).orElse(0.0F);
        this.sealCount = Adapters.INT.readJson(json.get("sealCount")).orElse(1);
    }
}
