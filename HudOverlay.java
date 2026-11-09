package com.gameland.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.BlockPos;

public class HudOverlay {
    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        int y = 8;
        int color = Config.current.purpleTheme ? 0xFFE2B8FF : 0xFFB8DCFF;
        if (Config.current.showFps) {
            context.fill(7, y - 2, 95, y + 12, 0x990B0618);
            context.drawText(client.textRenderer, "GL  " + client.getCurrentFps() + " FPS", 11, y, color, true);
            y += 17;
        }
        if (Config.current.showCoordinates) {
            BlockPos p = client.player.getBlockPos();
            String coords = "XYZ  " + p.getX() + " / " + p.getY() + " / " + p.getZ();
            context.fill(7, y - 2, 7 + client.textRenderer.getWidth(coords) + 10, y + 12, 0x990B0618);
            context.drawText(client.textRenderer, coords, 11, y, color, true);
        }

        // Small touch shortcut button. Tap its top-right GL badge to open the menu.
        int x = client.getWindow().getScaledWidth() - 34;
        context.fill(x - 4, 7, x + 25, 29, 0xCC18052E);
        context.fill(x - 4, 7, x + 25, 9, 0xFFB33CFF);
        context.drawText(client.textRenderer, "GL", x + 2, 14, 0xFFFFFFFF, true);
    }
}
