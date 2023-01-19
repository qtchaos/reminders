package dev.chaos.reminders.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

import static dev.chaos.reminders.SharedData.*;

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
        });
    }

    protected static int calculatePercentage(int current) {
        return (int) ((double) current / (double) STORE_REMIND_IN_TICKS * 100);
    }

    protected static int getColorFromTicks(int percentage) {
        if (percentage > 50) {
            return 0x1aff00;
        } else if (percentage > 25) {
            return 0xff7300;
        } else {
            return 0xf7160a;
        }
    }

    protected static String getRemindMessage() {
        String text;
        if (REMIND_MESSAGE.length() > 50) {
            text = REMIND_MESSAGE.substring(0, 50);
        } else {
            text = REMIND_MESSAGE;
        }
        int time = REMIND_IN_TICKS / 20 + 1;
        return text + " " + time + "s";
    }
}
