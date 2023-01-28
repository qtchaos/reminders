package dev.chaos.reminders.client;

import dev.chaos.reminders.client.event.ClientCommandHandler;
import dev.chaos.reminders.client.event.KeyInputHandler;
import dev.chaos.reminders.client.event.TextHandler;
import dev.chaos.reminders.client.event.TickHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.chaos.reminders.client.SharedData.MODID;
import static dev.chaos.reminders.client.utilities.Updates.checkForUpdates;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {
    public static Logger LOGGER;
    public static String VERSION;
    @Override
    public void onInitializeClient() {
        ModContainer mod = FabricLoader.getInstance()
                .getModContainer("reminders")
                .orElseThrow(NullPointerException::new);

        VERSION = mod.getMetadata()
                .getVersion()
                .getFriendlyString();

        LOGGER = LoggerFactory.getLogger(MODID);

        LOGGER.warn("Reminders v" + VERSION + " is loading...");
        checkForUpdates();
        TickHandler.register();
        ClientCommandHandler.register();
        KeyInputHandler.register();
        TextHandler.register();
    }
}
