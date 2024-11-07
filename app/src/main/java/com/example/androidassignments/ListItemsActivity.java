package com.example.androidassignments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ListItemsActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE =1;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ListItemsActivity", "onCreate called");
        setContentView(R.layout.activity_list_items);

        // Button to capture image using the camera
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ListItemsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ListItemsActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    dispatchTakePictureIntent();
                }
            }
        });

        // Button to go back to MainActivity
        Button mainButton = findViewById(R.id.buttonMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListItemsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button logoutButton = findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListItemsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Switch to show a toast message
        Switch mySwitch = findViewById(R.id.switch2);
        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Switch is On" : "Switch is Off";
            int duration = isChecked ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
            Toast.makeText(ListItemsActivity.this, message, duration).show();
        });

        // Checkbox to show a dialog box
        CheckBox myCheckBox = findViewById(R.id.checkBox2);
        myCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.button_dialog_message)
                        .setTitle(R.string.button_dialog_title)
                        .setPositiveButton(R.string.button_ok, (dialog, id) -> {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("Response", "Here is my response");
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        })
                        .setNegativeButton(R.string.button_cancel, (dialog, id) -> dialog.dismiss())
                        .show();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (Exception e) {
                Log.e("CameraError", "Error starting camera activity: " + e.getMessage());
            }
        } else {
            Toast.makeText(this, "No camera app available", Toast.LENGTH_SHORT).show();
            Log.e("CameraError", "No app available to handle camera intent");
        }
    }

    // Handle the result of the camera activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                ImageView imageView = findViewById(R.id.capturedImageView);
                if (imageBitmap != null) {
                    imageView.setImageBitmap(imageBitmap);
                } else {
                    Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No image captured", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with camera intent
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission is required to take pictures", Toast.LENGTH_SHORT).show();
                Log.e("PermissionError", "Camera permission denied");
            }
        }
    }

    // Lifecycle methods for logging
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ListItemsActivity", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ListItemsActivity", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ListItemsActivity", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ListItemsActivity", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ListItemsActivity", "onDestroy called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("ListItemsActivity", "onSaveInstanceState called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("ListItemsActivity", "onRestoreInstanceState called");
    }
}