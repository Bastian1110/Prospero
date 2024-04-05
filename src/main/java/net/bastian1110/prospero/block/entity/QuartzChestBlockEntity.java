package net.bastian1110.prospero.block.entity;

import net.bastian1110.prospero.block.custom.QuartzChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class QuartzChestBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;
    public QuartzChestBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.QUARTZ_CHEST_BE.get(), pPos, pBlockState);
        this.items = NonNullList.withSize(27, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level p_155062_, BlockPos p_155063_, BlockState p_155064_) {
                QuartzChestBlockEntity.this.playSound(p_155064_, SoundEvents.BARREL_OPEN);
                QuartzChestBlockEntity.this.updateBlockState(p_155064_, true);
            }

            protected void onClose(Level p_155072_, BlockPos p_155073_, BlockState p_155074_) {
                QuartzChestBlockEntity.this.playSound(p_155074_, SoundEvents.BARREL_CLOSE);
                QuartzChestBlockEntity.this.updateBlockState(p_155074_, false);
            }

            protected void openerCountChanged(Level p_155066_, BlockPos p_155067_, BlockState p_155068_, int p_155069_, int p_155070_) {
            }

            protected boolean isOwnContainer(Player p_155060_) {
                if (p_155060_.containerMenu instanceof ChestMenu) {
                    Container $$1 = ((ChestMenu)p_155060_.containerMenu).getContainer();
                    return $$1 == QuartzChestBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.items);
        }

    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag)) {
            ContainerHelper.loadAllItems(pTag, this.items);
        }

    }

    public int getContainerSize() {
        return 27;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    protected Component getDefaultName() {
        return Component.translatable("container.quartz_chest");
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return ChestMenu.threeRows(pId, pPlayer, this);
    }

    public void startOpen(Player pPlayer) {
        if (!this.remove && !pPlayer.isSpectator()) {
            this.openersCounter.incrementOpeners(pPlayer, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player pPlayer) {
        if (!this.remove && !pPlayer.isSpectator()) {
            this.openersCounter.decrementOpeners(pPlayer, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateBlockState(BlockState pState, boolean pOpen) {
        this.level.setBlock(this.getBlockPos(), (BlockState)pState.setValue(QuartzChestBlock.OPEN, pOpen), 3);
    }

    void playSound(BlockState pState, SoundEvent pSound) {
        Vec3i $$2 = ((Direction)pState.getValue(BarrelBlock.FACING)).getNormal();
        double $$3 = (double)this.worldPosition.getX() + 0.5 + (double)$$2.getX() / 2.0;
        double $$4 = (double)this.worldPosition.getY() + 0.5 + (double)$$2.getY() / 2.0;
        double $$5 = (double)this.worldPosition.getZ() + 0.5 + (double)$$2.getZ() / 2.0;
        this.level.playSound((Player)null, $$3, $$4, $$5, pSound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
