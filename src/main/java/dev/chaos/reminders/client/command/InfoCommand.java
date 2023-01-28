package dev.chaos.reminders.client.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static dev.chaos.reminders.client.MainClient.VERSION;
import static dev.chaos.reminders.client.SharedData.LATEST_VERSION;
import static dev.chaos.reminders.client.SharedData.OUTDATED;
import static dev.chaos.reminders.client.utilities.ChatLogging.log;

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
