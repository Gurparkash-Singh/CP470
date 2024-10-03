package com.example.androidassignments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListItemsActivity extends AppCompatActivity {

    private static final String activityName = "ListItemsActivity";
    private ImageButton imageButton;
    private Switch switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String functionName = "onCreate";
        Log.i(activityName, functionName + " in " + activityName);

        // Request camera permissions
        if (ContextCompat.checkSelfPermission(
                ListItemsActivity.this, "android.permission.CAMERA") !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ListItemsActivity.this, new String[]{
                    "android.permission.CAMERA"
            }, 1);
        }

        // Get the image button and checkbox
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        switchButton = (Switch) findViewById(R.id.switch1);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, 1);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ListItemsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    Toast.makeText(ListItemsActivity.this, "Switch is On", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ListItemsActivity.this, "Switch is Off", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void print(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(bitmap);
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