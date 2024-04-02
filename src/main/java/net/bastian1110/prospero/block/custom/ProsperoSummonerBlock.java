package net.bastian1110.prospero.block.custom;

import net.bastian1110.prospero.block.ModBlocks;
import net.bastian1110.prospero.entity.ModEntities;
import net.bastian1110.prospero.entity.custom.QuartzGolemEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.function.Predicate;

public class ProsperoSummonerBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING;
    @Nullable
    private BlockPattern quartzGolemBase;
    @Nullable
    private BlockPattern quartzGolemFull;
    public ProsperoSummonerBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pOldState.is(pState.getBlock())) {
            this.trySpawnGolem(pLevel, pPos);
        }
    }

    public boolean canSpawnGolem(LevelReader pLevel, BlockPos pPos) {
        return this.getOrCreateQuartzGolemBase().find(pLevel, pPos) != null || this.getOrCreateQuartzGolemBase().find(pLevel, pPos) != null;
    }

    private void trySpawnGolem(Level pLevel, BlockPos pPos) {
        BlockPattern.BlockPatternMatch match = this.getOrCreateQuartzGolemFull().find(pLevel, pPos);
        if (match != null) {
            QuartzGolemEntity golem = (QuartzGolemEntity) ModEntities.QUARTZ_GOLEM.get().create(pLevel);
            if (golem != null) {
                spawnGolemInWorld(pLevel, match, golem, match.getBlock(0,1,0).getPos());
            }
        }
    }

    private static void spawnGolemInWorld(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch, Entity pGolem, BlockPos pPos) {
        clearPatternBlocks(pLevel, pPatternMatch);
        pGolem.moveTo((double)pPos.getX() + 0.5, (double)pPos.getY() + 0.05, (double)pPos.getZ() + 0.5, 0.0F, 0.0F);
        pLevel.addFreshEntity(pGolem);
        Iterator var4 = pLevel.getEntitiesOfClass(ServerPlayer.class, pGolem.getBoundingBox().inflate(5.0)).iterator();

        while(var4.hasNext()) {
            ServerPlayer $$4 = (ServerPlayer)var4.next();
            CriteriaTriggers.SUMMONED_ENTITY.trigger($$4, pGolem);
        }

        updatePatternBlocks(pLevel, pPatternMatch);
    }

    private BlockPattern getOrCreateQuartzGolemBase() {
        if (this.quartzGolemBase == null) {
            this.quartzGolemBase = BlockPatternBuilder.start().aisle(new String[]{" ", "#"}).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.QUARTZ_BLOCK))).build();
        }

        return this.quartzGolemBase;
    }

    private BlockPattern getOrCreateQuartzGolemFull() {
        if (this.quartzGolemFull == null) {
            this.quartzGolemFull = BlockPatternBuilder.start().aisle(new String[]{"^", "#"}).where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.PROSPERO_SUMMONER_BLOCK.get()))).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.QUARTZ_BLOCK))).build();
        }

        return this.quartzGolemFull;
    }


    public static void clearPatternBlocks(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch) {
        for(int $$2 = 0; $$2 < pPatternMatch.getWidth(); ++$$2) {
            for(int $$3 = 0; $$3 < pPatternMatch.getHeight(); ++$$3) {
                BlockInWorld $$4 = pPatternMatch.getBlock($$2, $$3, 0);
                pLevel.setBlock($$4.getPos(), Blocks.AIR.defaultBlockState(), 2);
                pLevel.levelEvent(2001, $$4.getPos(), Block.getId($$4.getState()));
            }
        }

    }

    public static void updatePatternBlocks(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch) {
        for(int $$2 = 0; $$2 < pPatternMatch.getWidth(); ++$$2) {
            for(int $$3 = 0; $$3 < pPatternMatch.getHeight(); ++$$3) {
                BlockInWorld $$4 = pPatternMatch.getBlock($$2, $$3, 0);
                pLevel.blockUpdated($$4.getPos(), Blocks.AIR);
            }
        }

    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return (BlockState)this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{FACING});
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }
}
