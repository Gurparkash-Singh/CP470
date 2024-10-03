package com.example.androidassignments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private static final String activityName = "LoginActivity";
    private static final String preferencesFile = "preferences";
    private static final String preferenceEmailField = "defaultEmail";
    private Button loginButton;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String functionName = "onCreate";
        Log.i(activityName, functionName + " in " + activityName);

        // Get the Email Input and Login Button
        loginButton = (Button) findViewById(R.id.loginButton);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        // Get the default email from shared preferences
        String defaultEmail = loadPreferences();

        // set email field to default email
        emailField.setText(defaultEmail);

        // Add an event listener to the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get Email and Password from user
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Validate Email and Password
                if (TextUtils.isEmpty(email))
                {
                    createDialog(getResources().getString(R.string.emptyEmail));
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    createDialog(getResources().getString(R.string.invalidEmail));
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    createDialog(getResources().getString(R.string.emptyPassword));
                    return;
                }

                // Save email to shared Preferences
                savePreferences(email);

                // Show Main Activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String loadPreferences()
    {
        SharedPreferences preferences = getSharedPreferences(preferencesFile, MODE_PRIVATE);
        return preferences.getString(preferenceEmailField, "email@domain.com");
    }

    private void savePreferences(String defaultEmail)
    {
        SharedPreferences preferences = getSharedPreferences(preferencesFile, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(preferenceEmailField, defaultEmail);
        editor.apply();
    }

    private void createDialog(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String functionName = "onResume";
        Log.i(activityName, functionName + " in " + activityName);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String functionName = "onStart";
        Log.i(activityName, functionName + " in " + activityName);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String functionName = "onPause";
        Log.i(activityName, functionName + " in " + activityName);
    }

    @Override
    protected void onStop() {
        super.onStop();

        String functionName = "onStop";
        Log.i(activityName, functionName + " in " + activityName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String functionName = "onDestroy";
        Log.i(activityName, functionName + " in " + activityName);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String functionName = "onSaveInstanceState";
        Log.i(activityName, functionName + " in " + activityName);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String functionName = "onRestoreInstanceState";
        Log.i(activityName, functionName + " in " + activityName);
    }
}
