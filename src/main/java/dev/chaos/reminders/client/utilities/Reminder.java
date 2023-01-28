package dev.chaos.reminders.client.utilities;


import static dev.chaos.reminders.client.SharedData.LOOP_REMINDER;
import static dev.chaos.reminders.client.SharedData.REMIND_IN_TICKS;
import static dev.chaos.reminders.client.SharedData.STORE_PATH;
import static dev.chaos.reminders.client.utilities.ChatLogging.client;
import static dev.chaos.reminders.client.utilities.ChatLogging.log;

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
        client.setScreen(null);
    }
}
