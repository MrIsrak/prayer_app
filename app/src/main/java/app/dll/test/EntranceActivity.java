package app.dll.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import app.dll.test.PreferencesFuncs;

import com.google.android.gms.auth.api.identity.BeginSignInRequest; // Remove unused import
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EntranceActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;

    private Button enterAppButton;
    private SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;
    private TextInputLayout enterName;
    private EditText editEnterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        enterAppButton = findViewById(R.id.enter_btn);
        signInButton = findViewById(R.id.sign_in_button);
        enterName = findViewById(R.id.nameInput);
        editEnterName = enterName.getEditText();

        enterName.setOnClickListener(v -> {


        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        enterAppButton.setOnClickListener(v -> {
            String name = String.valueOf(editEnterName.getText());
            SharedPreferences usernamePrefs = getSharedPreferences("username", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = usernamePrefs.edit();
            editor.putString("username", name); // Ensure the key is "username"
            editor.apply();

            Intent intent = new Intent(EntranceActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });

        signInButton.setOnClickListener(v -> signIn());
    }

    private void signIn() {
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
                String name = account.getDisplayName();

                // Save to SharedPreferences
                SharedPreferences usernamePrefs = getSharedPreferences("username", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = usernamePrefs.edit();
                editor.putString("username", name); // Ensure the key is "username"
                editor.apply();

                Log.d("EntranceActivity", "Saved name: " + name);

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}
