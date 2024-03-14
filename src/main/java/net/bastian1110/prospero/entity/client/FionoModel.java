package net.bastian1110.prospero.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bastian1110.prospero.entity.animations.ModAnimationDefinitions;
import net.bastian1110.prospero.entity.custom.FionoEntity;
import net.bastian1110.prospero.entity.custom.RhinoEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class FionoModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart fiono;
    private final ModelPart head;

    public FionoModel(ModelPart root) {
        this.fiono = root.getChild("root");
        this.head = fiono.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -8.0F, 8.0F, 5.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-3.0F, -8.0F, -7.0F, 6.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 0.0F));

        PartDefinition front_right_leg = root.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -1.1054F, -0.6077F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, 8.0F));

        PartDefinition front_left_leg = root.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.0F, 9.0F));

        PartDefinition back_right_leg = root.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, -1.4079F, -2.231F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -2.0F, -6.0F));

        PartDefinition back_left_leg = root.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(34, 7).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -3.0F, -7.0F));

        PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(36, 34).addBox(0.0F, -10.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 34).addBox(0.0F, -11.0F, -9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 14).addBox(0.0F, -12.0F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 12).addBox(0.0F, -13.0F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 9).addBox(0.0F, -14.0F, -10.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 23).addBox(-2.0F, -14.0F, 7.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 0).addBox(-3.0F, -11.0F, 10.0F, 8.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -11.0F, 12.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 34).addBox(-2.0F, -16.0F, 8.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 12).addBox(2.0F, -16.0F, 8.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 1).addBox(2.0F, -14.0F, 11.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 0).addBox(-2.0F, -14.0F, 11.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-1.0F, -12.0F, 6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(43, 7).addBox(0.0F, -11.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(9, 6).addBox(0.0F, -11.0F, 13.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.FIONO_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((FionoEntity) entity).idleAnimationState, ModAnimationDefinitions.FIONO_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -10.0F, 10.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -6.0F, 10.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        fiono.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return fiono;
    }
}
