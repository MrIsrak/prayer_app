package app.dll.test.firebase;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class firebaseSetUp {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Method to store a string in Firestore using the username as the document ID
    public static void saveUserName(String userName) {
        // Create a map to hold the user data
        Map<String, Object> user = new HashMap<>();
        user.put("username", userName);

        // Write the data under a document with the username as the document ID
        db.collection("users").document(userName).set(user)
                .addOnSuccessListener(aVoid -> {

                    String fire = "fire";
                    // Data successfully written
                    Log.d(fire, "Username saved successfully in Firestore!");
                })
                .addOnFailureListener(e -> {
                    // Failed to write data
                    Log.d(userName,"Failed to save username in Firestore: " + e.getMessage());
                });
    }

}
