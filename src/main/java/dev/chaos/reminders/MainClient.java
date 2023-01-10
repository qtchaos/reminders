package dev.chaos.reminders;

import dev.chaos.reminders.event.ClientCommandHandler;
import dev.chaos.reminders.event.KeyInputHandler;
import dev.chaos.reminders.event.TickHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.chaos.reminders.SharedData.L_MODID;
import static dev.chaos.reminders.SharedData.MODID;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    @Override
    public void onInitializeClient() {
        LOGGER.info(L_MODID + " Registering tick handlers!");
        TickHandler.register();
        ClientCommandHandler.register();
        KeyInputHandler.register();
    }
}
