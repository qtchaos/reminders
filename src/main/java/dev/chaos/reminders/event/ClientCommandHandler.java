package dev.chaos.reminders.event;

import dev.chaos.reminders.command.RemindCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class ClientCommandHandler {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            RemindCommand.register(dispatcher);
        });
    }
}
