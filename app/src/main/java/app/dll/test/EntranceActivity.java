package app.dll.test;

import static androidx.core.content.ContextCompat.startActivity;
import static app.dll.test.userDataPrefs.userLocationData.LocationPermissons.updateLocState;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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
//import com.google.firebase.inappmessaging.model.Button;

import java.util.prefs.Preferences;

import app.dll.test.userDataPrefs.userPreferences.PreferencesFuncs;
import app.dll.test.userDataPrefs.themeUtils.ThemeUtils;
import app.dll.test.userDataPrefs.userLocationData.LocationPermissons;

public class EntranceActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleSignInClient googleSignInClient;

    private Button enterAppButton;
    private SignInButton signInButton;
    private TextInputLayout enterNameLayout;
    private EditText enterNameEditText;
    private Button locationButton;
    private ImageView profilePic;
    public static String profilePhotoUrl;

    //SharedPreferences setting up
    public static SharedPreferences locationPrefs;
    public static SharedPreferences userName;
    public static SharedPreferences themePrefs;
    public static SharedPreferences isLogin;


    //Initializing variable to sync enterences
    private boolean googleEnterence = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        // Applying theme
        themePrefs = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        ThemeUtils.setTheme();

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
        updateLocState(locationPrefs);

        // Button listeners
        locationButton.setOnClickListener(v -> LocationPermissons.getLocationPermission(this));

        enterAppButton.setOnClickListener(v -> {
            String name = enterNameEditText.getText().toString();
            updateLocState(locationPrefs);
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            } else if (!locationPrefs.getBoolean("locationPrefs", false)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
                Toast.makeText(this, "Please allow location access to enter the app", Toast.LENGTH_SHORT).show();
            } else {
                PreferencesFuncs.saveName(name);
                PreferencesFuncs.loginSate(); // Save login state
                navigateToMainMenu();  // Navigate to MainMenuActivity
            }
        });

        signInButton.setOnClickListener(v -> signInWithGoogle());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                PreferencesFuncs.locStae(locationPrefs, true);  // Update the location state
                Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show();
                navigateToMainMenu();  // Proceed to the main menu after permission is granted
            } else {
                // Location permission denied
                PreferencesFuncs.locStae(locationPrefs, false);
                Toast.makeText(this, "Location permission denied. You cannot proceed without granting location access.", Toast.LENGTH_SHORT).show();

            }
        }
    }
    // Method to sign in with Google
    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    //Handling the google sing in button
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
                    PreferencesFuncs.saveName(name);
                    // Check if location permission is granted
                    if (!locationPrefs.getBoolean("locationPrefs", false)) {
                        // Request location permission if not granted
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                LOCATION_PERMISSION_REQUEST_CODE);
                        Toast.makeText(this, "Please allow location access to enter the app", Toast.LENGTH_SHORT).show();
                    } else {
                        googleEnterence = true;
                        // Save login state
                        PreferencesFuncs.loginSate();
                        // If permission is already granted, navigate to the next activity
                        navigateToMainMenu();
                    }
                }
            } catch (ApiException e) {
                e.printStackTrace();  // Handle the error
            }
        }
    }
    // Method to navigate to MainMenuActivity
    private void navigateToMainMenu() {
        Intent intent = new Intent(EntranceActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();  // Close EntranceActivity so the user can't go back to the login screen
    }
    private void openLocationSettings(){
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
}
