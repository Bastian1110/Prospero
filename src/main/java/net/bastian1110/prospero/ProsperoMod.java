package net.bastian1110.prospero;

import com.mojang.logging.LogUtils;
import net.bastian1110.prospero.block.ModBlocks;
import net.bastian1110.prospero.block.entity.ModBlockEntities;
import net.bastian1110.prospero.entity.ModEntities;
import net.bastian1110.prospero.entity.client.FionoRenderer;
import net.bastian1110.prospero.entity.client.QuartzGolemRenderer;
import net.bastian1110.prospero.entity.client.RhinoRenderer;
import net.bastian1110.prospero.item.ModCreativeModTabs;
import net.bastian1110.prospero.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ProsperoMod.MOD_ID)
public class ProsperoMod {
    public static final String MOD_ID = "prospero";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ProsperoMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
            EntityRenderers.register(ModEntities.FIONO.get(), FionoRenderer::new);
            EntityRenderers.register(ModEntities.QUARTZ_GOLEM.get(), QuartzGolemRenderer::new);
        }
    }
}
