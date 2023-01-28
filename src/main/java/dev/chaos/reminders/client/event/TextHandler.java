package dev.chaos.reminders.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

import static dev.chaos.reminders.client.SharedData.NOTE_TEXT;
import static dev.chaos.reminders.client.SharedData.REMIND_IN_TICKS;
import static dev.chaos.reminders.client.SharedData.REMIND_MESSAGE;
import static dev.chaos.reminders.client.SharedData.STORE_REMIND_IN_TICKS;

public class TextHandler {
    public static void register() {
        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            String text;
            int color;
            if (REMIND_IN_TICKS > 0) {
                color = getColorFromTicks(calculatePercentage(REMIND_IN_TICKS));

                text = getRemindMessage();
                MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "Reminders:", 2, 2, 0xFFFFFF);
                MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, text, 2, 12, color);
            }
            if (NOTE_TEXT.length() > 0) {
                int temp_y = 32;
                if (REMIND_IN_TICKS == 0) {
                    temp_y = 2;
                }

                MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "Note:", 2, temp_y, 0xFFFFFF);
                String[] lines = NOTE_TEXT.split("(?<=\\G.{30})");
                for (String line : lines) {
                    if (line.charAt(0) == ' ') {
                        line = line.substring(1);
                    }
                    MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, line, 2, temp_y + 10, 0xFFFFFF);
                    temp_y += 10;
                }
            }

        });
    }

    private static int calculatePercentage(int current) {
        return (int) ((double) current / (double) STORE_REMIND_IN_TICKS * 100);
    }

    private static int getColorFromTicks(int percentage) {
        if (percentage > 50) {
            return 0x1aff00;
        } else if (percentage > 25) {
            return 0xff7300;
        } else {
            return 0xf7160a;
        }
    }

    private static String getRemindMessage() {
        String text;
        if (REMIND_MESSAGE.length() > 50) {
            text = REMIND_MESSAGE.substring(0, 50);
        } else {
            text = REMIND_MESSAGE;
        }
        int time = REMIND_IN_TICKS / 20 + 1;

        return convertTime(time, text);
    }

    private static String convertTime(int time, String text) {
        int seconds = time % 60;
        if (time >= 60) {
            int minutes = time / 60 % 60;
            if (time >= 3600) {
                int hours = time / 3600;
                text += " " + hours + "h";
            }
            text += " " + minutes + "m";
        }
        text += " " + seconds + "s";
        return text;
    }
}
