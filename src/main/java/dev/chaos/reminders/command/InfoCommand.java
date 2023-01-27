package dev.chaos.reminders.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static dev.chaos.reminders.MainClient.VERSION;
import static dev.chaos.reminders.SharedData.*;
import static dev.chaos.reminders.utilities.ChatLogging.log;

public class InfoCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("reminders")
            .then(ClientCommandManager.literal("info")
                .executes(context -> {
                    log("Reminders v" + VERSION + ", developed by chaos.", true);
                    if (OUTDATED) {
                        log("There is a new version available: " + LATEST_VERSION, true);
                    }
                    log("Use the /remind <seconds> <message> command to set a reminder.", true);
                    return 1;
                })
            )
        );
    }
}
