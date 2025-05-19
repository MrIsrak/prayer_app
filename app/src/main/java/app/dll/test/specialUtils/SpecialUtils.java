package app.dll.test.specialUtils;

import static android.content.Context.MODE_PRIVATE;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.pdf.PdfDocument;
import android.view.View;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import app.dll.test.R;

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

    public static int findPageWithText(String pdfPath, String searchText, View view) throws IOException {
        InputStream inputStream = view.getResources().getAssets().open(pdfPath);
        PDDocument document = PDDocument.load(inputStream);
        try {
            PDFTextStripper stripper = new PDFTextStripper();
            searchText = "פרק " + searchText; // Prepend "פרק " (Hebrew for "Chapter") to searchText

            // Loop through each page
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                stripper.setStartPage(i + 1); // Pages are 1-based in PDFTextStripper
                stripper.setEndPage(i + 1);
                String pageText = stripper.getText(document);

                // Check if the searchText exists in this page's text
                if (pageText.contains(searchText)) {
                    document.close(); // Close the document before returning
                    inputStream.close(); // Close the input stream
                    return i; // Return the 0-based page index
                }
            }

            // If text is not found, return -1
            document.close();
            inputStream.close();
            return -1;
        } finally {
            // Ensure resources are closed even if an exception occurs
            if (document != null) {
                document.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }




}
