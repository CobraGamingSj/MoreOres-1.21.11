package org.cobra.moreores.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.machine.GemCrystallizerBlockEntity;

import java.util.List;

@Environment(EnvType.CLIENT)
public class GemCrystallizerScreen extends AbstractGemMachineScreen<GemCrystallizerBlockEntity, GemCrystallizerScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    
    private static final Identifier TEXTURE = MoreOresModInitializer.id("textures/gui/container/gem_crystallizer/gem_crystallizer_gui.png");
    private static final Identifier START_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/start.png");
    private static final Identifier PAUSE_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/pause.png");
    private static final Identifier RESUME_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/resume.png");
    private static final Identifier STOP_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/stop.png");
    
    private static final Identifier EMPTY_RUBY_TEXTURE = MoreOresModInitializer.id("container/slot/empty_ruby");
    private static final Identifier EMPTY_SAPPHIRE_TEXTURE = MoreOresModInitializer.id("container/slot/empty_sapphire");
    private static final Identifier EMPTY_GARNET_TEXTURE = MoreOresModInitializer.id("container/slot/empty_garnet");
    private static final Identifier EMPTY_PERIDOT_TEXTURE = MoreOresModInitializer.id("container/slot/empty_peridot");
    private static final Identifier EMPTY_JADE_TEXTURE = MoreOresModInitializer.id("container/slot/empty_jade");
    private static final Identifier EMPTY_PYROPE_TEXTURE = MoreOresModInitializer.id("container/slot/empty_pyrope");
    private static final Identifier EMPTY_KYAWTHUITE_TEXTURE = MoreOresModInitializer.id("container/slot/empty_kyawthuite");
    private static final Identifier EMPTY_RADIANT_TEXTURE = MoreOresModInitializer.id("container/slot/empty_radiant");
    private static final Identifier EMPTY_QUARTZ_TEXTURE = MoreOresModInitializer.id("container/slot/empty_quartz");
    
    private final CyclingSlotIcon energyIngotSlotIcon = new CyclingSlotIcon(3);
    private final CyclingSlotIcon inputBeforeIngotSlotIcon = new CyclingSlotIcon(0);
    private final CyclingSlotIcon inputAfterIngotSlotIcon = new CyclingSlotIcon(1);
    
    public GemCrystallizerScreen(GemCrystallizerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 196;
        this.backgroundWidth = 207;
    }

    @Override
    protected Identifier getBackgroundTexture() {
        return TEXTURE;
    }

    @Override
    protected Identifier getStartButtonTexture() {
        return START_BUTTON;
    }

    @Override
    protected Identifier getPauseButtonTexture() {
        return PAUSE_BUTTON;
    }

    @Override
    protected Identifier getResumeButtonTexture() {
        return RESUME_BUTTON;
    }

    @Override
    protected Identifier getStopButtonTexture() {
        return STOP_BUTTON;
    }

    @Override
    protected int getStartButtonPosX() {
        return 112;
    }

    @Override
    protected int getStartButtonPosY() {
        return 8;
    }

    @Override
    protected int getPauseButtonPosX() {
        return 160;
    }

    @Override
    protected int getPauseButtonPosY() {
        return 8;
    }

    @Override
    protected int getResumeButtonPosX() {
        return 112;
    }

    @Override
    protected int getResumeButtonPosY() {
        return 56;
    }

    @Override
    protected int getStopButtonPosX() {
        return 160;
    }

    @Override
    protected int getStopButtonPosY() {
        return 56;
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();
        this.energyIngotSlotIcon.updateTexture(getEnergyIngotSlotTexture());
        this.inputBeforeIngotSlotIcon.updateTexture(getBothInputSlotTexture());
        this.inputAfterIngotSlotIcon.updateTexture(getBothInputSlotTexture());
    }

    private List<Identifier> getEnergyIngotSlotTexture() {
        return List.of(MoreOresModInitializer.id("container/slot/empty_ingot"), MoreOresModInitializer.id("container/slot/energy_ingot_faded"));
    }

    private List<Identifier> getBothInputSlotTexture() {
        return List.of(EMPTY_RUBY_TEXTURE, EMPTY_SAPPHIRE_TEXTURE, EMPTY_GARNET_TEXTURE, EMPTY_KYAWTHUITE_TEXTURE, 
                EMPTY_PERIDOT_TEXTURE, EMPTY_JADE_TEXTURE, EMPTY_PYROPE_TEXTURE, EMPTY_RADIANT_TEXTURE, EMPTY_QUARTZ_TEXTURE);
    }
    
    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if(this.handler.isCrystallizing()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 70, y + 41, 207, 0, 11, this.handler.progressGetter(), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void renderRedstoneDust(DrawContext context, int x, int y) {
        int k = handler.getRedstoneDust();
        int l = MathHelper.clamp((k * 16 + 10000 - 1) / 10000, 0, 16);

        int startX = x + 92;
        int startY = y + 79;
        int endY = y + 83;

        context.fillGradient(startX, startY, startX + l, endY, Colors.RED, -7667712);
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        super.drawBackground(context, deltaTicks, mouseX, mouseY);
        renderRadiantDust(context, this.x, this.y);
        if(this.handler.getBlockEntity().energyStack().isEmpty()) {
           this.energyIngotSlotIcon.render(this.handler, context, deltaTicks, this.x, this.y);
        }
        if(this.handler.getBlockEntity().ingredientStack().isEmpty() && this.handler.getBlockEntity().ingredientAfterStack().isEmpty()) {
            this.inputBeforeIngotSlotIcon.render(this.handler, context, deltaTicks, this.x, this.y);
            this.inputAfterIngotSlotIcon.render(this.handler, context, deltaTicks, this.x, this.y);
        }
    }

    private void renderRadiantDust(DrawContext context, int x, int y) {
        int k = handler.getDustCount();
        int l = MathHelper.clamp((18 * k + 10000 - 1) / 10000, 0, 18);
        if(l > 0) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 38, y + 97, 207, 29, l, 4, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void renderEnergyHandler(DrawContext context, int x, int y) {
        int energyBarSize = MathHelper.ceil(this.handler.calculateEnergyAmountPercentage() * 44);

        int startY = y + 43 + 44 - energyBarSize;
        int endY = y + 43 + 44;

        int barX1 = x + 13;
        int barX2 = barX1 + 16;

        context.fillGradient(barX1, startY, barX2, endY, Colors.PURPLE, Colors.RED);
    }

    @Override
    public void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        String name = this.handler.getBlockEntity().getDisplayName().getString();
        int x = 8;
        int y = 8;
        context.drawText(this.textRenderer, name, x, y, Colors.BLACK, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        int energyBarSize = MathHelper.ceil(this.handler.calculateEnergyAmountPercentage() * 44);
        int radiantBarWidth = MathHelper.clamp((18 * handler.getDustCount() + 10000 - 1) / 10000, 0, 18);
        int redstoneBarWidth = MathHelper.clamp((handler.getRedstoneDust() * 16 + 10000 - 1) / 10000, 0, 16);
        if (isPointWithinBounds(13, 43 + 44 - energyBarSize, 16, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergyAmount() + " / " + this.handler.getEnergyCapacity() + " J").formatted(Formatting.DARK_AQUA, Formatting.BOLD), mouseX, mouseY);
        }
        if (isPointWithinBounds(38, 97, radiantBarWidth, 4, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getDustCount() + " Particles").formatted(Formatting.RED),  mouseX, mouseY);
        }
        if (isPointWithinBounds(92, 79, redstoneBarWidth, 4, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getRedstoneDust() + " Particles").formatted(Formatting.RED), mouseX, mouseY);
        }
    }
}