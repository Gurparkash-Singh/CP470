package com.example.androidassignments;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String activityName = "MainActivity";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String functionName = "onCreate";
        Log.i(activityName, functionName + " in " + activityName);

        // Get the button
        button = (Button) findViewById(R.id.button);

        // Button event listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show List Items Activity
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10)
        {
            Log.i(activityName, "Returned to " + activityName + " onActivityResult");
        }
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