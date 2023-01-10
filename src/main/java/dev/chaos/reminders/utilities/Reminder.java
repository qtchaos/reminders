package dev.chaos.reminders.utilities;

import static dev.chaos.reminders.SharedData.*;
import static dev.chaos.reminders.utilities.ChatLogging.log;

public class Reminder {
    public static void cancelReminder() {
        if (REMIND_IN_TICKS > 0 || LOOP_REMINDER) {
            log("Cancelling reminder.");
            REMIND_IN_TICKS = 0;
            LOOP_REMINDER = false;
            STORE_PATH = "";
        } else {
            log("You don't have a reminder set.");
        }
    }
}
