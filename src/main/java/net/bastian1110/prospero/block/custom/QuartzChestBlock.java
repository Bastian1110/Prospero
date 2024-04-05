package net.bastian1110.prospero.block.custom;

import net.bastian1110.prospero.block.entity.QuartzChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class QuartzChestBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty OPEN;
    public QuartzChestBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(OPEN, false));
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity $$6 = pLevel.getBlockEntity(pPos);
            if ($$6 instanceof QuartzChestBlockEntity) {
                pPlayer.openMenu((QuartzChestBlockEntity)$$6);
            }

            return InteractionResult.CONSUME;
        }
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity $$5 = pLevel.getBlockEntity(pPos);
            if ($$5 instanceof Container) {
                Containers.dropContents(pLevel, pPos, (Container)$$5);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockEntity $$4 = pLevel.getBlockEntity(pPos);
        if ($$4 instanceof QuartzChestBlockEntity) {
            ((QuartzChestBlockEntity)$$4).recheckOpen();
        }
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new QuartzChestBlockEntity(pPos, pState);
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (pStack.hasCustomHoverName()) {
            BlockEntity $$5 = pLevel.getBlockEntity(pPos);
            if ($$5 instanceof QuartzChestBlockEntity) {
                ((QuartzChestBlockEntity)$$5).setCustomName(pStack.getHoverName());
            }
        }
    }

    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(pLevel.getBlockEntity(pPos));
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return (BlockState)pState.setValue(FACING, pRotation.rotate((Direction)pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation((Direction)pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{FACING, OPEN});
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return (BlockState)this.defaultBlockState().setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
    }

    static {
        FACING = BlockStateProperties.FACING;
        OPEN = BlockStateProperties.OPEN;
    }
}
