package dev.chaos.reminders;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Util;

import static dev.chaos.reminders.utilities.PreviousReminder.loopPreviousReminder;
import static dev.chaos.reminders.utilities.PreviousReminder.setPreviousReminder;
import static dev.chaos.reminders.utilities.Reminder.cancelReminder;

public class CustomScreen extends Screen {
    public CustomScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        int buttonWidth = 74;
        assert client != null;

        addDrawableChild(ButtonWidget.builder(Text.literal("Repeat previous reminder."), b -> setPreviousReminder())
                        .dimensions(width / 2 - buttonWidth, height / 2 - 8, buttonWidth * 2, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Loop previous reminder."), b -> loopPreviousReminder())
                .dimensions(width / 2 - buttonWidth, height / 2 - 32, buttonWidth * 2, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Cancel reminder."), b -> cancelReminder())
                .dimensions(width / 2 - buttonWidth, height / 2 + 16, buttonWidth * 2, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> client.setScreen(null))
                .dimensions(width - 10 - buttonWidth, 10, buttonWidth, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Website"), b -> {
                    Util.getOperatingSystem().open("https://modrinth.com/mod/reminders");
                    client.setScreen(null);
                }).dimensions(width - 10 - buttonWidth, 32, buttonWidth, 20).build());

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        drawCenteredText(matrices, textRenderer, title, width / 2, 20, 16777215);

        super.render(matrices, mouseX, mouseY, delta);
    }
}
