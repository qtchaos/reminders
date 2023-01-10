package dev.chaos.reminders.event;

import dev.chaos.reminders.CustomScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import static dev.chaos.reminders.SharedData.*;
import static dev.chaos.reminders.utilities.ChatLogging.log;
import static dev.chaos.reminders.utilities.Toast.useToast;

public class TickHandler {
    protected static void notifyClient(MinecraftClient client) {
        log(REMIND_MESSAGE);
        client.getToastManager().add(useToast());
        client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 2F));
    }
    public static void register() {
        ClientTickEvents.START_WORLD_TICK.register((world) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (SHOW_UI) {
                client.setScreen(new CustomScreen(Text.of("Reminders")));
                SHOW_UI = false;
            }
            if (LOOP_REMINDER) {
                REMIND_IN_TICKS--;
                if (REMIND_IN_TICKS == 0) {
                    REMIND_IN_TICKS = STORE_REMIND_IN_TICKS;
                    notifyClient(client);
                }
                return;
            }
            if (REMIND_IN_TICKS > 0) {
                REMIND_IN_TICKS--;
                if (REMIND_IN_TICKS == 0) {
                    notifyClient(client);
                }
            }
        });
    }
}
