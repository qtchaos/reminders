package dev.chaos.reminders.utilities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.chaos.reminders.SharedData.L_MODID;

public class ChatLogging {
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static void log(String message) {
        assert client.player != null;
        Text text = Text.of(L_MODID + ": ");
        MutableText mutableText = text.copyContentOnly();
        mutableText.formatted(Formatting.AQUA);
        Formatting formattingWhite = Formatting.WHITE;
        client.player.sendMessage(
            mutableText.append((Text.literal(message))
                    .formatted(formattingWhite))
        );
    }
}
