package net.bastian1110.prospero.event;

import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.entity.ModEntities;
import net.bastian1110.prospero.entity.custom.FionoEntity;
import net.bastian1110.prospero.entity.custom.QuartzGolemEntity;
import net.bastian1110.prospero.entity.custom.RhinoEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProsperoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
        event.put(ModEntities.FIONO.get(), FionoEntity.createAttributes().build());
        event.put(ModEntities.QUARTZ_GOLEM.get(), QuartzGolemEntity.createAttributes().build());


    }
}
