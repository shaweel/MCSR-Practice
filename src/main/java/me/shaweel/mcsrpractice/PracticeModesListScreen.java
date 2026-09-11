package me.shaweel.mcsrpractice;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TranslatableComponent;

public class PracticeModesListScreen extends Screen {
	private Screen lastScreen;

	private static final int TITLE_Y_PADDING = 8;
	private static final int FONT_COLOR = 0xFFFFFF;
	private static final int LARGE_BUTTON_WIDTH = 150;
	private static final int SMALL_BUTTON_WIDTH = 72;
	private static final int BUTTON_HEIGHT = 20;
	private static final int CENTER_BUTTON_GAP = 8;
	private static final int SMALL_BUTTON_GAP = 6;
	private static final int VERTICAL_BUTTON_GAP = 6;

	private int leftMostButtonX() {
		return this.width / 2 - CENTER_BUTTON_GAP / 2 - LARGE_BUTTON_WIDTH;
	}

	private int upperRightMostButtonX() {
		return this.width / 2 + CENTER_BUTTON_GAP / 2;
	}

	private int lowerMiddleLeftButtonX() {
		return this.width / 2 - CENTER_BUTTON_GAP / 2 - SMALL_BUTTON_WIDTH;
	}

	private int lowerMiddleRightButtonX() {
		return upperRightMostButtonX();
	}

	private int lowerRightMostButtonX() {
		return this.width / 2 + CENTER_BUTTON_GAP / 2 + SMALL_BUTTON_WIDTH + SMALL_BUTTON_GAP;
	}

	private int upperButtonY() {
		return this.height - VERTICAL_BUTTON_GAP * 2 - BUTTON_HEIGHT * 2;
	}

	private int lowerButtonY() {
		return this.height - VERTICAL_BUTTON_GAP - BUTTON_HEIGHT;
	}

	public PracticeModesListScreen(Screen screen) {
		super(new TranslatableComponent("mcsr-practice.menu.practiceModes.title"));
		lastScreen = screen;
	}

	private void drawButtons() {
		this.addButton(new Button(
			leftMostButtonX(),
			upperButtonY(),
			LARGE_BUTTON_WIDTH,
			BUTTON_HEIGHT,
			new TranslatableComponent("mcsr-practice.menu.practiceModes.buttons.practiceSelected"),
			button -> {}
		));

		this.addButton(new Button(
			upperRightMostButtonX(),
			upperButtonY(),
			LARGE_BUTTON_WIDTH,
			BUTTON_HEIGHT,
			new TranslatableComponent("mcsr-practice.menu.practiceModes.buttons.configure"),
			button -> {}
		));

		this.addButton(new Button(
			leftMostButtonX(),
			lowerButtonY(),
			SMALL_BUTTON_WIDTH,
			BUTTON_HEIGHT,
			new TranslatableComponent("mcsr-practice.menu.practiceModes.buttons.favorite"),
			button -> {}
		));

		this.addButton(new Button(
			lowerMiddleLeftButtonX(),
			lowerButtonY(),
			SMALL_BUTTON_WIDTH,
			BUTTON_HEIGHT,
			new TranslatableComponent("mcsr-practice.menu.practiceModes.buttons.selectAll"),
			button -> {}
		));

		this.addButton(new Button(
			lowerMiddleRightButtonX(),
			lowerButtonY(),
			SMALL_BUTTON_WIDTH,
			BUTTON_HEIGHT,
			new TranslatableComponent("mcsr-practice.menu.practiceModes.buttons.deselect"),
			button -> {}
		));

		this.addButton(new Button(
			lowerRightMostButtonX(),
			lowerButtonY(),
			SMALL_BUTTON_WIDTH,
			BUTTON_HEIGHT,
			CommonComponents.GUI_BACK,
			button -> { Minecraft.getInstance().setScreen(lastScreen); }
		));
	}
	
	@Override
	public void init() {
		drawButtons();
	}
	
	@Override 
	public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
		super.renderBackground(poseStack);
		this.drawCenteredString(poseStack, this.font, this.title, this.width / 2, TITLE_Y_PADDING, FONT_COLOR);

		super.render(poseStack, mouseX, mouseY, delta);
	}
}
