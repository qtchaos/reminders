package dev.chaos.reminders.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.chaos.reminders.MainClient.VERSION;
import static dev.chaos.reminders.MainClient.LOGGER;
import static dev.chaos.reminders.SharedData.LATEST_VERSION;
import static dev.chaos.reminders.SharedData.OUTDATED;

public class Updates {
    private static String matchRegex(String input) {
        Pattern pattern = Pattern.compile("(?<=\\[)(.*?)(?<=\\]\\})");
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1) : null;
    }
    public static void checkForUpdates() throws IOException {
        JSONObject json = readJsonFromUrl();
        String latestVersion = json.getString("version_number");
        if (latestVersion != null) {
            int latestVersionParsed = Integer.parseInt(latestVersion.replace(".", ""));
            int currentVersionParsed = Integer.parseInt(VERSION.replace(".", ""));
            if (currentVersionParsed < latestVersionParsed) {
                OUTDATED = true;
                LATEST_VERSION = latestVersion;
                LOGGER.warn("You are using an outdated version of Reminders! Please update to " + latestVersion + "!");
            }
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl() throws IOException, JSONException {
        try (InputStream is = new URL("https://api.modrinth.com/v2/project/reminders/version").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(Objects.requireNonNull(matchRegex(jsonText)));
        }
    }
}
