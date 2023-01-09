package dev.chaos.reminders.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static dev.chaos.reminders.SharedData.REMIND_MESSAGE;
import static dev.chaos.reminders.utilities.ChatLogging.log;
import static dev.chaos.reminders.SharedData.REMIND_IN_TICKS;

public class RemindCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("remind")
            .then(ClientCommandManager.argument("time", IntegerArgumentType.integer(1, 100000))
                .then(ClientCommandManager.argument("message", StringArgumentType.greedyString())
                    .executes(context -> {
                        int time = IntegerArgumentType.getInteger(context, "time");
                        String message = StringArgumentType.getString(context, "message");
                        REMIND_IN_TICKS = time * 20;
                        REMIND_MESSAGE = message;
                        log("You will be reminded in " + time + " seconds.");
                        return 1;
                    })
                )
            )
        );
        dispatcher.register(ClientCommandManager.literal("remind")
            .then(ClientCommandManager.literal("cancel")
                .executes(context -> {
                    if (REMIND_IN_TICKS > 0) {
                        log("Cancelling reminder.");
                        REMIND_IN_TICKS = 0;
                        REMIND_MESSAGE = "";
                    } else {
                        log("You don't have a reminder set.");
                    }
                    return 1;
                })
            )
        );
    }
}
