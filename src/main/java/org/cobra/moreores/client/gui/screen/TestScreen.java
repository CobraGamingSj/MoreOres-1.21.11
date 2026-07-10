package org.cobra.moreores.client.gui.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class TestScreen extends HandledScreen<TestScreenHandler> {
    public TestScreen(TestScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        
    }
}
