package dev.ugamii.whoareyou.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import dev.ugamii.whoareyou.config.WhoAreYouConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
	@Inject(
			at = @At("HEAD"),
			method = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;hasLabel(Lnet/minecraft/entity/LivingEntity;)Z",
			cancellable = true
	)
	private void viewOwnLabel(LivingEntity ent, CallbackInfoReturnable<Boolean> ci) {
		if (WhoAreYouConfig.enabledOwnName && ent == MinecraftClient.getInstance().cameraEntity)
		{
			ci.setReturnValue(MinecraftClient.isHudEnabled());
		}
		if (WhoAreYouConfig.enabledOtherPlayersName && ent != MinecraftClient.getInstance().cameraEntity)
		{
			ci.setReturnValue(MinecraftClient.isHudEnabled());
		}
	}
}

