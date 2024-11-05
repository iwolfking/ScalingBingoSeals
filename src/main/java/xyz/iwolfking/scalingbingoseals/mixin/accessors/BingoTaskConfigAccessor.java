package xyz.iwolfking.scalingbingoseals.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "iskallia.vault.task.BingoTask$Config", remap = false)
public interface BingoTaskConfigAccessor {
    @Accessor("width")
    void setWidth(int width);

    @Accessor("height")
    void setHeight(int height);
}