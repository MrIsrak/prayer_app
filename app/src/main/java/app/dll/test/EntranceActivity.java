package app.dll.test;

import static app.dll.test.userDataPrefs.userLocationData.LocationPermissons.getLocationPermission;
import static app.dll.test.userDataPrefs.userLocationData.LocationPermissons.updateLocState;
import static app.dll.test.userDataPrefs.userNotificationsData.NotificationPermissons.getNotificationPermission;
import static app.dll.test.userDataPrefs.userNotificationsData.NotificationPermissons.updateNotificationState;
import static app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs.saveName;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;

public class EntranceActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 2;  // Added constant for notification permission
    private GoogleSignInClient googleSignInClient;

    private Button enterAppButton;
    private SignInButton signInButton;
    private TextInputLayout enterNameLayout;
    private EditText enterNameEditText;
    private Button locationButton;
    private ImageView profilePic;
    public static String profilePhotoUrl;

    // SharedPreferences setup
    public static SharedPreferences locationPrefs;
    public static SharedPreferences notificationPrefs;
    public static SharedPreferences userName;
    public static SharedPreferences themePrefs;
    public static SharedPreferences isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Applying theme
        themePrefs = getSharedPreferences("themePrefs", MODE_PRIVATE);
        ThemeUtils.setTheme(this);

        // Initialize SharedPreferences for userName
        userName = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);  // Initialize userName here

        String savedUsername = userName.getString("username", null);

        // Check if the user is logged in using Google or username
        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleAccount != null || savedUsername != null) {
            // User is already logged in, skip login and go to MainMenuActivity
            navigateToMainMenu();
            return;
        }

        // If not logged in, proceed with the normal entrance setup
        setContentView(R.layout.activity_entrance);

        // Google SIGN-IN initialization
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize other UI elements
        enterAppButton = findViewById(R.id.enter_btn);
        signInButton = findViewById(R.id.sign_in_button);
        enterNameLayout = findViewById(R.id.nameInput);
        enterNameEditText = enterNameLayout.getEditText();
        locationButton = findViewById(R.id.loc_btn);
        profilePic = findViewById(R.id.profile_photo);

        // Storing location permission state
        locationPrefs = getSharedPreferences("locationPrefs", Context.MODE_PRIVATE);
        updateLocState();

        // Soring notification permission state
        notificationPrefs = getSharedPreferences("notificationPrefs", Context.MODE_PRIVATE);
        updateNotificationState();

        // Button listeners
        locationButton.setOnClickListener(v -> getLocationPermission(this));

        enterAppButton.setOnClickListener(v -> {
            String name = enterNameEditText.getText().toString();
            saveName(name);
            updateLocState();

            if (name.isEmpty()) {
                Toast.makeText(this, R.string.enterName, Toast.LENGTH_SHORT).show();
            } else if (!locationPrefs.getBoolean("locationPrefs", false) || !notificationPrefs.getBoolean("notificationPrefs", false)) {
                checkPermissionsAndProceed();}
        });

        signInButton.setOnClickListener(v -> signInWithGoogle());
    }

    private void checkPermissionsAndProceed() {
        boolean hasLocationPermission = locationPrefs.getBoolean("locationPrefs", false);
        boolean hasNotificationPermission = notificationPrefs.getBoolean("notificationPrefs", false);

        // Make both permissions necessary
        if (!hasLocationPermission || !hasNotificationPermission) {
            // Request both location and notification permissions
            getLocationPermission(this);
            getNotificationPermission(this);
        } else {
            // Both permissions are granted, proceed to main menu
            PreferencesFuncs.loginSate();  // Save login state
            navigateToMainMenu();  // Navigate to MainMenuActivity
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                PreferencesFuncs.locState(true);  // Update the location state
                Toast.makeText(this, R.string.accessLocGranted, Toast.LENGTH_SHORT).show();
                navigateToMainMenu();  // Proceed to the main menu after permission is granted
            } else {
                // Location permission denied
                PreferencesFuncs.locState(false);
                Toast.makeText(this, R.string.accessLocDenied, Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Notification permission granted
                PreferencesFuncs.notState(true);
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show();
            } else {
                PreferencesFuncs.notState(false);
                // Notification permission denied
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to sign in with Google
    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Handling the Google sign-in button
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    String name = account.getGivenName();
                    // If the profile photo exists, store the URL
                    if (account.getPhotoUrl() != null) {
                        profilePhotoUrl = account.getPhotoUrl().toString();
                    }
                    // Save Google Sign-In username in SharedPreferences
                    saveName(name);
                    // Check if location permission is granted
                    checkPermissionsAndProceed();
                }
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Method to navigate to MainMenuActivity
    private void navigateToMainMenu() {
        Intent intent = new Intent(EntranceActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();  // Close EntranceActivity so the user can't go back to the login screen
    }
}
