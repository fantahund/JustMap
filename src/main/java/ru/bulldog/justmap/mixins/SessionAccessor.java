package ru.bulldog.justmap.mixins;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftServer.class)
public interface SessionAccessor {
	@Accessor(value = "session")
	LevelStorage.Session getServerSession();
}
