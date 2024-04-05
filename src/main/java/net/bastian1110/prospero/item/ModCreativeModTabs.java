package net.bastian1110.prospero.item;

import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProsperoMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());

                        pOutput.accept(ModItems.METAL_DETECTOR.get());


                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());

                        pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());

                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());

                        pOutput.accept(ModBlocks.PROSPERO_SUMMONER_BLOCK.get());
                        pOutput.accept(ModBlocks.QUARTZ_CHEST.get());

                        pOutput.accept(ModItems.RHINO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.FIONO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.QUARTZ_GOLEM_EGG.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
