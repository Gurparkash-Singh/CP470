package com.example.androidassignments;

import static java.lang.System.exit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LoginActivity", "onCreate called");
        setContentView(R.layout.activity_login);

        Button testToolbarButton = findViewById(R.id.buttonTestToolbar);
        testToolbarButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, TestToolbar.class);
            startActivity(intent);
        });

        // Fetch the last used email from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String savedEmail = prefs.getString("DefaultEmail", "");  // Fetch the saved email (default is empty)
        EditText emailField = findViewById(R.id.LoginEmail);  // Assuming ID is LoginEmail
        EditText passwordField = findViewById(R.id.passwordEditText); // Assuming this is the ID for the password field

        emailField.setText(savedEmail);  // Set the saved email to the email field

        // Button to go back to MainActivity
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();  // Get the email from the EditText field
                String password = passwordField.getText().toString().trim();

                if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.isEmpty()) {  // Check if email is not empty
                    // Save the entered email in SharedPreferences
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("DefaultEmail", email);  // Save the email in SharedPreferences
                    editor.apply();  // Apply changes to SharedPreferences

                    // Log for debugging
                    Log.i("LoginActivity", "Email saved: " + email);

                    // Start MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // If email is empty, show a Toast message
                    Toast.makeText(LoginActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button to go to ListItemsActivity
        //Button listItemsButton = findViewById(R.id.buttonListItems);
        //listItemsButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent(LoginActivity.this, ListItemsActivity.class);
        //        startActivity(intent);
        //    }
        //});
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LoginActivity", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LoginActivity", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LoginActivity", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LoginActivity", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LoginActivity", "onDestroy called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("LoginActivity", "onSaveInstanceState called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("LoginActivity", "onRestoreInstanceState called");
    }
}
