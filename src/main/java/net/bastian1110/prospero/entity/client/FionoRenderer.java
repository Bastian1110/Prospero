package net.bastian1110.prospero.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.entity.custom.FionoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FionoRenderer extends MobRenderer <FionoEntity, FionoModel<FionoEntity>>  {
    public FionoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FionoModel<>(pContext.bakeLayer(ModModelLayers.FIONO_LAYER)), 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(FionoEntity fionoEntity) {
        return new ResourceLocation(ProsperoMod.MOD_ID, "textures/entity/fiono.png");
    }

    @Override
    public void render(FionoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pMatrixStack.scale(0.6f,0.6f,0.6f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
