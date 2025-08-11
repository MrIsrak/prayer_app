package app.dll.test.presentation.utils.specialUtils;

import static android.content.Context.MODE_PRIVATE;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;


public class SpecialUtils {
    public static void saveCurrentActivity(Class<?> activityClass, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lastActivity", activityClass.getName());
        editor.apply();
    }
    // Check if the app has already been activated once
    public static boolean isAppActivatedOnce(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getBoolean("isActivatedOnce", false);
    }

    // Set the app as activated once
    public static void setAppActivatedOnce(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isActivatedOnce", true);
        editor.apply();
    }
        public static void restartApp(Context context) {
            SharedPreferences activated = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
            if(!isAppActivatedOnce(context)){

                setAppActivatedOnce(context);

                Intent intent = new Intent(context, context.getClass());

                // Add flags to clear the activity stack and start fresh
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                // Start the default activity
                context.startActivity(intent);

                // Kill the current process to ensure a fresh restart
                android.os.Process.killProcess(android.os.Process.myPid());
            }

        }
    public static String numberToHebrew(int number) {
        if (number <= 0) return "Invalid number";

        String[] hebrewLetters = {"", "א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט",
                "י", "כ", "ל", "מ", "נ", "ס", "ע", "פ", "צ", "ק",
                "ר", "ש", "ת"};
        int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 20, 30, 40, 50, 60, 70, 80, 90, 100,
                200, 300, 400};

        StringBuilder result = new StringBuilder();
        int remaining = number;

        // Handle special cases like 15 and 16
        if (number == 15) return "ט״ו";
        if (number == 16) return "ט״ז";

        // Convert the number
        for (int i = values.length - 1; i >= 0; i--) {
            while (remaining >= values[i]) {
                result.append(hebrewLetters[i]);
                remaining -= values[i];
            }
        }

        // Add gershayim if more than one letter
        if (result.length() > 1) {
            result.insert(result.length() - 1, "״");
        } else if (result.length() == 1 && number > 9) {
            result.append("׳"); // Single quote for single-digit numbers above 9
        }

        return result.toString();
    }



    public static String cleanFootnotes(String string){
        return string
            .replaceAll("<sup[^>]*footnote-marker[^>]*>.*?</sup>", "")
            .replaceAll("<sup[^>]*endFootnote[^>]*>.*?</sup>", "")
            .replaceAll("<sup>.*?</sup>", "")
            .replaceAll("<i[^>]*footnote[^>]*>.*?</i>", "")
            .replaceAll("\\s*<br>\\s*", "<br>")
            .replaceAll(" +", " ")
            .trim();
    }


}
