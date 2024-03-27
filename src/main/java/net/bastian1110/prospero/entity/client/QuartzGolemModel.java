package net.bastian1110.prospero.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bastian1110.prospero.entity.animations.ModAnimationDefinitions;
import net.bastian1110.prospero.entity.custom.FionoEntity;
import net.bastian1110.prospero.entity.custom.QuartzGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class QuartzGolemModel<T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart quartz_golem;
    private final ModelPart head;
    public QuartzGolemModel(ModelPart root){
        this.quartz_golem = root.getChild("quartz_golem");
        this.head = quartz_golem.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition quartz_golem = ((PartDefinition) partdefinition).addOrReplaceChild("quartz_golem", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition left_arm = quartz_golem.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(10, 31).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -6.0F));

        PartDefinition head = quartz_golem.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -0.6F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition right_leg = quartz_golem.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(27, 26).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.0F));

        PartDefinition left_leg = quartz_golem.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(22, 16).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -3.0F));

        PartDefinition right_arm = quartz_golem.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 6.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.QUARTZ_GOLEM_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((QuartzGolemEntity) entity).idleAnimationState, ModAnimationDefinitions.QUARTZ_GOLEM_IDLE, ageInTicks, 1f);

    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -10.0F, 10.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        quartz_golem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return quartz_golem;
    }
}
