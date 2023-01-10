package dev.chaos.reminders.event;

import dev.chaos.reminders.SharedData;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class KeyInputHandler {
    private static final String KEY_CATEGORY_REMINDERS = "key.reminders.main";
    private static final String KEY_UI = "key.reminders.keyUI";
    private static KeyBinding ReminderUIKey;

    public static void registerKeyInput() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ReminderUIKey.wasPressed()) {
                SharedData.SHOW_UI = true;
            }
        });
    }

    public static void register() {
        ReminderUIKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_UI,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_REMINDERS
        ));

        registerKeyInput();
    }
}
