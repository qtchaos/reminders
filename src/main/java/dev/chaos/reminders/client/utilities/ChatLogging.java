package dev.chaos.reminders.client.utilities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.chaos.reminders.client.SharedData.MODID;

public class ChatLogging {
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static void log(String message) {
        assert client.player != null;
        Text text = Text.of(String.format("[%s]: ", MODID));
        MutableText mutableText = text.copyContentOnly();
        mutableText.formatted(Formatting.AQUA);
        Formatting formattingWhite = Formatting.WHITE;
        client.player.sendMessage(
            mutableText.append((Text.literal(message))
                    .formatted(formattingWhite))
        );
    }

    public static void log(String message, boolean hiddenIdentifier) {
        assert client.player != null;
        if (hiddenIdentifier) {
            client.player.sendMessage(Text.of(message));
        } else {
            log(message);
        }
    }
}
