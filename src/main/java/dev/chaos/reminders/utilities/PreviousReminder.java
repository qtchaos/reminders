package dev.chaos.reminders.utilities;

import static dev.chaos.reminders.SharedData.*;
import static dev.chaos.reminders.utilities.ChatLogging.client;
import static dev.chaos.reminders.utilities.ChatLogging.log;

public class PreviousReminder {
    public static void setPreviousReminder() {
        if (REMIND_MESSAGE.equals("")) {
            log("You have no previous reminders.");
            client.setScreen(null);
            return;
        }
        if (REMIND_IN_TICKS > 0) {
            log("You already have a reminder set.");
            client.setScreen(null);
            return;
        }

        REMIND_IN_TICKS = STORE_REMIND_IN_TICKS;
        log("Your previous reminder has been set.");
        client.setScreen(null);
    }

    public static void loopPreviousReminder() {
        if (REMIND_MESSAGE.equals("")) {
            log("You have no previous reminders.");
            client.setScreen(null);
            return;
        }

        log("Looping your previous reminder.");
        REMIND_IN_TICKS = STORE_REMIND_IN_TICKS;
        LOOP_REMINDER = true;
        client.setScreen(null);
    }
}
