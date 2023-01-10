package dev.chaos.reminders.utilities;

public class Random {

    public static String getRandomString() {
        // https://www.baeldung.com/java-random-string
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 4;
        java.util.Random random = new java.util.Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
