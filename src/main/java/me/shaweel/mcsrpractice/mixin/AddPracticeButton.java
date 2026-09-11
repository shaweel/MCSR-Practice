package me.shaweel.mcsrpractice.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.TranslatableComponent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;

@Mixin(TitleScreen.class)
public class AddPracticeButton {
	@Shadow private void realmsButtonClicked() {};

	@Inject(at = @At("HEAD"), method = "createNormalMenuOptions", cancellable = true)
	private void addPracticeButton(int i, int j, CallbackInfo callbackInfo) {
		TitleScreen titleScreen = (TitleScreen)(Object)this;

		ScreenAccessor screenAccessor = (ScreenAccessor)titleScreen;

		screenAccessor.invokeAddButton(
			new Button(
				titleScreen.width / 2 - 100, 
				i, 
				200, 20, 
				new TranslatableComponent("mcsr-practice.menu.practice"), 
				button -> Minecraft.getInstance().setScreen(new SelectWorldScreen(titleScreen))
			)
		);

		screenAccessor.invokeAddButton(
			new Button(
				titleScreen.width / 2 - 100, 
				i + j * 1, 
				200, 20, 
				new TranslatableComponent("menu.singleplayer"), 
				(button) -> Minecraft.getInstance().setScreen(new SelectWorldScreen(titleScreen))
			)
		);
		boolean bl = Minecraft.getInstance().allowsMultiplayer();
		Button.OnTooltip onTooltip = bl ? Button.NO_TOOLTIP : (button, poseStack, ix, jx) -> {
			if (!button.active) {
				titleScreen.renderTooltip(
					poseStack, 
					Minecraft.getInstance().font.split(new TranslatableComponent("title.multiplayer.disabled"), 
					Math.max(titleScreen.width / 2 - 43, 170)), 
					ix, 
					jx
				);
			}

		};

		((Button)screenAccessor.invokeAddButton(
			new Button(
				titleScreen.width / 2 - 100, 
				i + j * 2, 
				200, 20, 
				new TranslatableComponent("menu.multiplayer"), 
				(button) -> {
					Screen screen = (Screen)(
						Minecraft.getInstance().options.skipMultiplayerWarning 
						? new JoinMultiplayerScreen(titleScreen) 
						: new SafetyScreen(titleScreen)
					);
					Minecraft.getInstance().setScreen(screen);
				}, 
				onTooltip
			)
		)).active = bl;

		callbackInfo.cancel();
	}
}