package net.bastian1110.prospero.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bastian1110.prospero.ProsperoMod;
import net.bastian1110.prospero.entity.custom.FionoEntity;
import net.bastian1110.prospero.entity.custom.QuartzGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class QuartzGolemRenderer extends MobRenderer<QuartzGolemEntity, QuartzGolemModel<QuartzGolemEntity>> {
    public QuartzGolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new QuartzGolemModel<>(pContext.bakeLayer(ModModelLayers.QUARTZ_GOLEM_LAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(QuartzGolemEntity quartzGolemEntity) {
        return new ResourceLocation(ProsperoMod.MOD_ID, "textures/entity/quartz_golem.png");
    }

    @Override
    public void render(QuartzGolemEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
