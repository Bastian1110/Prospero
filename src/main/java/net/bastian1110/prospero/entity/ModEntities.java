package net.bastian1110.prospero.entity;

import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.entity.custom.FionoEntity;
import net.bastian1110.prospero.entity.custom.QuartzGolemEntity;
import net.bastian1110.prospero.entity.custom.RhinoEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ProsperoMod.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino", ()-> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5F).build("rhino"));

    public static  final RegistryObject<EntityType<FionoEntity>> FIONO =
            ENTITY_TYPES.register("fiono", () -> EntityType.Builder.of(FionoEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("fiono"));

    public static final RegistryObject<EntityType<QuartzGolemEntity>> QUARTZ_GOLEM =
            ENTITY_TYPES.register("quartz_golem", ()-> EntityType.Builder.of(QuartzGolemEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("quartz_golem"));



    public static void register(IEventBus eventbus){
        ENTITY_TYPES.register(eventbus);
    }
}
