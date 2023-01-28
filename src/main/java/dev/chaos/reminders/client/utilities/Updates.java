package dev.chaos.reminders.client.utilities;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static dev.chaos.reminders.client.MainClient.VERSION;
import static dev.chaos.reminders.client.MainClient.LOGGER;
import static dev.chaos.reminders.client.SharedData.LATEST_VERSION;
import static dev.chaos.reminders.client.SharedData.OUTDATED;

public class Updates {
    private static final String VERSION_URL = "https://api.modrinth.com/v2/project/reminders/version";
    public static void checkForUpdates() {
        ModrinthResponse[] response;
        try {
            URL url = new URL(VERSION_URL);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            response = new Gson().fromJson(reader, ModrinthResponse[].class);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("Failed to check for updates!");
            LATEST_VERSION = VERSION;
            return;
        }

        String latestVersion = response[0].getVersion_number();
        int latestVersionParsed = Integer.parseInt(latestVersion.replace(".", ""));
        int currentVersionParsed = Integer.parseInt(VERSION.replace(".", ""));
        if (currentVersionParsed < latestVersionParsed) {
            OUTDATED = true;
            LATEST_VERSION = latestVersion;
            LOGGER.warn("You are using an outdated version of Reminders! Please update to " + latestVersion + "!");
        }
    }

    private static class ModrinthResponse {
        public String version_number;

        public String getVersion_number() {
            return version_number;
        }
    }
}
