package dev.chaos.reminders.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.Util;

import static dev.chaos.reminders.client.utilities.PreviousReminder.loopPreviousReminder;
import static dev.chaos.reminders.client.utilities.PreviousReminder.setPreviousReminder;
import static dev.chaos.reminders.client.utilities.Reminder.cancelReminder;
import static dev.chaos.reminders.client.SharedData.NOTE_TEXT;

public class CustomScreen extends Screen {
    public CustomScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        int buttonWidth = 74;
        assert client != null;
        int TEXTFIELD_X;
        int TEXTFIELD_Y;

        if (width <= 427 && height <= 240) {
            TEXTFIELD_X = width / 2 - 75;
            TEXTFIELD_Y = (int) (height / 1.25);
        } else {
            TEXTFIELD_X = width / 2 - 75;
            TEXTFIELD_Y = height / 2 + 74;
        }

        TextFieldWidget textField = new TextFieldWidget(
                textRenderer,
                TEXTFIELD_X,
                TEXTFIELD_Y,
                150,
                20,
                Text.of("Enter text here"));

        textField.setMaxLength(256);
        if (NOTE_TEXT.length() > 0) {
            textField.setText(NOTE_TEXT);
        }

        textField.setChangedListener(s -> {
            if (s.length() > 0) {
                NOTE_TEXT = s;
            } else {
                NOTE_TEXT = "";
            }
        });

        addDrawableChild(ButtonWidget.builder(Text.literal("Loop previous reminder."), b -> loopPreviousReminder())
                .dimensions(width / 2 - buttonWidth, height / 2 - 32, buttonWidth * 2, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Repeat previous reminder."), b -> setPreviousReminder())
                        .dimensions(width / 2 - buttonWidth, height / 2 - 8, buttonWidth * 2, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Cancel reminder."), b -> cancelReminder())
                .dimensions(width / 2 - buttonWidth, height / 2 + 16, buttonWidth * 2, 20).build());

        // Top Right Social Buttons
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> client.setScreen(null))
                .dimensions(width - 10 - buttonWidth, 10, buttonWidth, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Website"), b -> {
            Util.getOperatingSystem().open("https://modrinth.com/mod/reminders");
            client.setScreen(null);
        }).dimensions(width - 10 - buttonWidth, 32, buttonWidth, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Source"), b -> {
            Util.getOperatingSystem().open("https://github.com/qtchaos/reminders");
            client.setScreen(null);
        }).dimensions(width - 10 - buttonWidth, 54, buttonWidth, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Discord"), b -> {
            Util.getOperatingSystem().open("https://discord.gg/AyaZ5EkpMd");
            client.setScreen(null);
        }).dimensions(width - 10 - buttonWidth, 76, buttonWidth, 20).build());

        addDrawableChild(new TextWidget(TEXTFIELD_X - 25, TEXTFIELD_Y - 20, 200, 20, Text.of("Write yourself some notes."), textRenderer));

        addDrawableChild(textField);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        drawCenteredText(matrices, textRenderer, title, width / 2, 20, 16777215);

        super.render(matrices, mouseX, mouseY, delta);
    }
}
