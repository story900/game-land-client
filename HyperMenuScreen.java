package com.gameland.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HyperMenuScreen extends Screen {
    private String category = "CLIENT";
    private final List<ButtonWidget> categoryButtons = new ArrayList<>();

    public HyperMenuScreen() {
        super(Text.literal("Game Land Hyper Menu"));
    }

    @Override
    protected void init() {
        categoryButtons.clear();
        String[] cats = {"COMBAT", "VISUAL", "MOVEMENT", "PLAYER", "MISC", "RENDER", "CLIENT"};
        int x = Math.max(12, width / 2 - 220);
        int y = Math.max(48, height / 2 - 120);
        for (int i = 0; i < cats.length; i++) {
            final String c = cats[i];
            ButtonWidget b = ButtonWidget.builder(Text.literal(c), btn -> {
                category = c;
                clearAndInit();
            }).dimensions(x, y + i * 34, 112, 28).build();
            addDrawableChild(b);
            categoryButtons.add(b);
        }

        int px = x + 126;
        int py = y;
        if (category.equals("CLIENT") || category.equals("VISUAL") || category.equals("RENDER")) {
            addToggle(px, py, "Show FPS", () -> Config.current.showFps, v -> Config.current.showFps = v);
            addToggle(px, py + 38, "Show Coordinates", () -> Config.current.showCoordinates, v -> Config.current.showCoordinates = v);
            addToggle(px, py + 76, "Compact HUD", () -> Config.current.compactHud, v -> Config.current.compactHud = v);
            addToggle(px, py + 114, "Purple Theme", () -> Config.current.purpleTheme, v -> Config.current.purpleTheme = v);
        } else {
            addDrawableChild(ButtonWidget.builder(Text.literal("No safe modules in this category yet"), b -> {})
                    .dimensions(px, py, 230, 28).build());
        }

        addDrawableChild(ButtonWidget.builder(Text.literal("SAVE SETTINGS"), b -> {
            Config.save();
            b.setMessage(Text.literal("SAVED!"));
        }).dimensions(px, py + 170, 230, 30).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("BACK"), b -> close())
                .dimensions(x, py + 170, 112, 30).build());
    }

    private void addToggle(int x, int y, String label, java.util.function.Supplier<Boolean> getter,
                           java.util.function.Consumer<Boolean> setter) {
        addDrawableChild(ButtonWidget.builder(toggleText(label, getter.get()), b -> {
            setter.accept(!getter.get());
            b.setMessage(toggleText(label, getter.get()));
        }).dimensions(x, y, 230, 30).build());
    }

    private Text toggleText(String label, boolean enabled) {
        return Text.literal(label + "  [" + (enabled ? "ON" : "OFF") + "]");
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int left = Math.max(8, width / 2 - 230);
        int top = Math.max(28, height / 2 - 150);
        int right = Math.min(width - 8, left + 370);
        int bottom = Math.min(height - 8, top + 230);
        int purple = Config.current.purpleTheme ? 0xFF9B35FF : 0xFF6AA9FF;
        context.fill(left - 8, top - 30, right + 8, bottom + 12, 0xE8100824);
        context.fill(left - 8, top - 30, right + 8, top - 27, purple);
        context.drawText(textRenderer, "GAME LAND CLIENT  /  HYPER MENU", left, top - 20, 0xFFFFFFFF, true);
        context.drawText(textRenderer, "CATEGORY: " + category, left + 126, top - 2, purple, true);
        context.drawText(textRenderer, "Right Shift  •  Touch shortcut: top-right GL", left, bottom - 2, 0xFFBCA6D9, false);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
