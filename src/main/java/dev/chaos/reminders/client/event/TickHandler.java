package dev.chaos.reminders.client.event;

import dev.chaos.reminders.client.CustomScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;


import static dev.chaos.reminders.client.SharedData.SHOW_UI;
import static dev.chaos.reminders.client.SharedData.OUTDATED;
import static dev.chaos.reminders.client.SharedData.LOOP_REMINDER;
import static dev.chaos.reminders.client.SharedData.REMIND_IN_TICKS;
import static dev.chaos.reminders.client.SharedData.STORE_REMIND_IN_TICKS;
import static dev.chaos.reminders.client.utilities.ChatLogging.log;
import static dev.chaos.reminders.client.utilities.Toast.useToast;

public class TickHandler {
    private static void notifyClient(MinecraftClient client) {
        client.getToastManager().add(useToast());
        client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 2F));
    }
    public static void register() {
        ClientTickEvents.START_WORLD_TICK.register((world) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (OUTDATED) {
                log("You are using an outdated version of Reminders! Please update to the latest version.");
                OUTDATED = false;
            }
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
