package net.bastian1110.prospero.event;

import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.entity.client.FionoModel;
import net.bastian1110.prospero.entity.client.ModModelLayers;
import net.bastian1110.prospero.entity.client.QuartzGolemModel;
import net.bastian1110.prospero.entity.client.RhinoModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProsperoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FIONO_LAYER, FionoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.QUARTZ_GOLEM_LAYER, QuartzGolemModel::createBodyLayer);
    }
}
