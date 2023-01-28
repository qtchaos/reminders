package dev.chaos.reminders.client.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static dev.chaos.reminders.client.SharedData.REMIND_IN_TICKS;
import static dev.chaos.reminders.client.SharedData.REMIND_MESSAGE;
import static dev.chaos.reminders.client.SharedData.STORE_REMIND_IN_TICKS;

public class RemindCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("remind")
            .then(ClientCommandManager.argument("time", IntegerArgumentType.integer(1, 86400))
                .then(ClientCommandManager.argument("message", StringArgumentType.greedyString())
                    .executes(context -> {
                        int time = IntegerArgumentType.getInteger(context, "time");
                        String message = StringArgumentType.getString(context, "message");

                        REMIND_IN_TICKS = time * 20;
                        REMIND_MESSAGE = message;
                        STORE_REMIND_IN_TICKS = REMIND_IN_TICKS;
                        return 1;
                    })
                )
            )
        );
    }
}
