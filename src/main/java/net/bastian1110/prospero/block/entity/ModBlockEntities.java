package net.bastian1110.prospero.block.entity;

import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ProsperoMod.MOD_ID);

        public static final RegistryObject<BlockEntityType<QuartzChestBlockEntity>> QUARTZ_CHEST_BE =
            BLOCK_ENTITIES.register("quartz_chest_be", () ->
                    BlockEntityType.Builder.of(QuartzChestBlockEntity::new,
                            ModBlocks.QUARTZ_CHEST.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}