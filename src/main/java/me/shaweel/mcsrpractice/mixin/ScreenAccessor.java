package me.shaweel.mcsrpractice.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

@Mixin(Screen.class)
public interface ScreenAccessor {
	@Invoker("addButton") <T extends AbstractWidget> T invokeAddButton(T abstractWidget);
}
