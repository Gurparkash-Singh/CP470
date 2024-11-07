package com.example.androidassignments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidassignments.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "This will do something in the future!", Snackbar.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        if (id == R.id.action_one) {
            Log.d("Toolbar", "Option 1 selected");
            Snackbar.make(findViewById(R.id.main), customMessage != null ? customMessage : "You selected item 1", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.action_two) {
            Log.d("Toolbar", "Option 2 selected");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent = new Intent(TestToolbar.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User cancelled the dialog
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (id == R.id.action_three) {
            Log.d("Toolbar", "Option 3 selected");
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
            ImageView imageView = dialogView.findViewById(R.id.custom_image);
            imageView.setImageResource(R.drawable.airplane);
            EditText editText = dialogView.findViewById(R.id.custom_edittext);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView)
                    .setTitle("Enter New Message")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            customMessage = editText.getText().toString();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User cancelled the dialog
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "Version 1.0, by Akif Rahman", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(mi);
        }
        return true;
    }

    private String customMessage;
}
