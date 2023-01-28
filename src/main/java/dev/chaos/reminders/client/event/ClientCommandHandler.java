package dev.chaos.reminders.client.event;

import dev.chaos.reminders.client.command.InfoCommand;
import dev.chaos.reminders.client.command.RemindCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class ClientCommandHandler {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            RemindCommand.register(dispatcher);
            InfoCommand.register(dispatcher);
        });
    }
}
