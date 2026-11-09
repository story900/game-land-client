package com.gameland.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class GameLandClient implements ClientModInitializer {
    private KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        Config.load();
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.gameland_hypermenu.open",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.gameland"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(new HyperMenuScreen());
                else if (client.currentScreen instanceof HyperMenuScreen) client.setScreen(null);
            }
        });
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> HudOverlay.render(drawContext));
    }
}
