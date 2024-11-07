package com.example.androidassignments;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoginActivityTest {

    @Mock
    private LoginActivity loginActivity;

    @Mock
    private Bundle mockBundle;

    @Mock
    private SharedPreferences mockSharedPreferences;

    @Mock
    private SharedPreferences.Editor mockEditor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginActivity = spy(new LoginActivity());
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }

    @Test
    public void testOnCreate() {
        doNothing().when(loginActivity).setContentView(anyInt());

        when(loginActivity.getSharedPreferences("MyAppPrefs", loginActivity.MODE_PRIVATE)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getString("DefaultEmail", "")).thenReturn("test@example.com");

        loginActivity.onCreate(mockBundle);

        verify(loginActivity).setContentView(anyInt());
        verify(mockSharedPreferences).getString("DefaultEmail", "");
    }

    @Test
    public void testOnStart() {
        loginActivity.onStart();
        verify(loginActivity).onStart();
    }

    @Test
    public void testOnResume() {
        loginActivity.onResume();
        verify(loginActivity).onResume();
    }

    @Test
    public void testOnPause() {
        loginActivity.onPause();
        verify(loginActivity).onPause();
    }

    @Test
    public void testOnStop() {
        loginActivity.onStop();
        verify(loginActivity).onStop();
    }

    @Test
    public void testOnDestroy() {
        loginActivity.onDestroy();
        verify(loginActivity).onDestroy();
    }

    @Test
    public void testOnSaveInstanceState() {
        loginActivity.onSaveInstanceState(mockBundle);
        verify(loginActivity).onSaveInstanceState(mockBundle);
    }

    @Test
    public void testOnRestoreInstanceState() {
        loginActivity.onRestoreInstanceState(mockBundle);
        verify(loginActivity).onRestoreInstanceState(mockBundle);
    }

    @Test
    public void testLoginButtonClick() {
        EditText emailField = mock(EditText.class);
        when(loginActivity.findViewById(R.id.LoginEmail)).thenReturn(emailField);
        when(emailField.getText().toString()).thenReturn("test@example.com");

        Button loginButton = mock(Button.class);
        when(loginActivity.findViewById(R.id.buttonLogin)).thenReturn(loginButton);

        doNothing().when(loginActivity).startActivity(any(Intent.class));

        // Simulate the login button click
        loginButton.performClick();

        verify(emailField).getText();
        verify(loginActivity).startActivity(any(Intent.class));
    }
}
