package app.dll.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

public class EntranceActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;

    private Button enterAppButton;
    private SignInButton signInButton;
    private TextInputLayout enterNameLayout;
    private EditText enterNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        // Check if the user is already logged in (Custom login or Google Sign-In)
        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);

        // Check if the user is logged in using Google
        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(this);

        if (googleAccount != null || savedUsername != null) {
            // User is already logged in, skip login and go to MainMenuActivity
            navigateToMainMenu();
            return;
        }

        // Set up UI elements
        enterAppButton = findViewById(R.id.enter_btn);
        signInButton = findViewById(R.id.sign_in_button);
        enterNameLayout = findViewById(R.id.nameInput);
        enterNameEditText = enterNameLayout.getEditText();

        // Configure Google Sign-In options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Custom login using the "Enter App" button
        enterAppButton.setOnClickListener(v -> {
            String name = enterNameEditText.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            } else {
                // Save entered name to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", name);
                editor.apply();
                Log.d("Name^", name);

                // Navigate to MainMenuActivity
                navigateToMainMenu();
            }
        });

        // Google Sign-In button
        signInButton.setOnClickListener(v -> signInWithGoogle());
    }

    // Method to sign in with Google
    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    String name = account.getDisplayName();

                    // Save Google Sign-In username in SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", name);
                    editor.apply();

                    // Navigate to MainMenuActivity
                    navigateToMainMenu();
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
}
